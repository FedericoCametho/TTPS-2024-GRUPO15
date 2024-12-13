import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';;
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

import { MenuService } from '../services/menu.service';
import { Comida } from '../model/carta/producto/comida';
import { ComidaService } from '../services/comida.service';
import { TipoComida } from '../model/carta/producto/tipo-comida.enum';
import { MenuRequest } from '../model/carta/producto/request/menuRequest';

@Component({
  selector: 'app-menu-update',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './menu-update.component.html',
  styleUrl: './menu-update.component.css'
})
export class MenuUpdateComponent {
  menuForm: FormGroup;
  entradas: Comida[] = [];
  bebidas: Comida[] = [];
  platosPrincipales: Comida[] = [];
  postres: Comida[] = [];
  menuId: number;
  menuRequest: MenuRequest = new MenuRequest();

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private menuService: MenuService,
    private comidaService: ComidaService
  ) {
    this.menuForm = this.fb.group({
      titulo: ['', [Validators.required]],
      foto: [''],
      precio: ['', [Validators.required, Validators.min(0)]],
      veggie: [false, [Validators.required]],
      comidas: this.fb.group({ 
        entrada: [''],
        bebida: [''],
        platoPrincipal: [''],
        postre: ['']
      })
    });
    this.menuId = this.route.snapshot.params['id'];
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        this.menuRequest.foto = reader.result as string;
      };

      reader.readAsDataURL(file); // Leer el archivo como Base64
    }
  }

  ngOnInit(): void {
    this.menuService.getMenuById(this.menuId).subscribe((menu) => {
      this.menuForm.patchValue({
        titulo: menu.nombre,
        foto: menu.foto,
        precio: menu.precio,
        veggie: menu.veggie,
        comidas: {
          entrada: menu.comidas.find(c => c.tipoComida === 'ENTRADA')?.id,
          bebida: menu.comidas.find(c => c.tipoComida === 'BEBIDA')?.id,
          platoPrincipal: menu.comidas.find(c => c.tipoComida === 'PLATO_PRINCIPAL')?.id,
          postre: menu.comidas.find(c => c.tipoComida === 'POSTRE')?.id
        }
      });
    });

    this.comidaService.getComidasByTipo(TipoComida.ENTRADA).subscribe((resp) => {
      this.entradas = resp;
    });

    this.comidaService.getComidasByTipo(TipoComida.BEBIDA).subscribe((resp) => {
      this.bebidas = resp;
    });

    this.comidaService.getComidasByTipo(TipoComida.PLATO_PRINCIPAL).subscribe((resp) => {
      this.platosPrincipales = resp;
    });

    this.comidaService.getComidasByTipo(TipoComida.POSTRE).subscribe((resp) => {
      this.postres = resp;
    });
  }

  get titulo() { return this.menuForm.get('titulo'); }
  get foto() { return this.menuForm.get('foto'); }
  get precio() { return this.menuForm.get('precio'); }
  get esVegano() { return this.menuForm.get('esVegano'); }
  get entrada() { return this.menuForm.get('comidas.entrada'); }
  get bebida() { return this.menuForm.get('comidas.bebida'); }
  get platoPrincipal() { return this.menuForm.get('comidas.platoPrincipal'); }
  get postre() { return this.menuForm.get('comidas.postre'); }

  onSubmit(): void {
    if (this.menuForm.valid) {
      const { titulo, precio, esVegano, comidas } = this.menuForm.value;
      const { entrada, bebida, platoPrincipal, postre } = comidas;
      const comidasArray = [entrada, bebida, platoPrincipal, postre].filter(comida => comida !== null && comida !== undefined && comida !== '');
      this.menuRequest.id =  this.menuId;
      this.menuRequest.nombre = titulo;
      this.menuRequest.precio = precio;
      this.menuRequest.veggie = esVegano;
      this.menuRequest.comidas = comidasArray;
        
      this.menuService.update(this.menuRequest, this.menuId).subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: 'Menú actualizado',
            text: 'El menú ha sido actualizado correctamente',
            confirmButtonText: 'OK'
          }).then(() => {
            this.router.navigate(['/menu-list']);
          });
        },
        error: (error) => {
          console.error('Error al actualizar el menú:', error);
        }
      });
    } else {
      console.log('Formulario no válido');
    }
  }
}
