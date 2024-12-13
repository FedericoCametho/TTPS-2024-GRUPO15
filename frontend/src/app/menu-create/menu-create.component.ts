import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import Swal from 'sweetalert2';

import { MenuRequest } from '../model/carta/producto/request/menuRequest';
import { ComidaService } from '../services/comida.service';
import { MenuService } from '../services/menu.service';
import { Comida } from '../model/carta/producto/comida';
import { TipoComida } from '../model/carta/producto/tipo-comida.enum';

@Component({
  selector: 'app-menu-create',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './menu-create.component.html',
  styleUrls: ['./menu-create.component.css']
})
export class MenuCreateComponent {
  menuForm: FormGroup;
  entradas: Comida[] = [];
  bebidas: Comida[] = [];
  platosPrincipales: Comida[] = [];
  postres: Comida[] = [];
  menuRequest: MenuRequest = new MenuRequest();


  constructor(
    private fb: FormBuilder,
    private comidaService: ComidaService,
    private menuService: MenuService,
    private router: Router
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
  }

  ngOnInit(): void {
    this.comidaService.getComidasByTipo(TipoComida.ENTRADA).subscribe((data: Comida[]) => {
      this.entradas = data;
    });
    this.comidaService.getComidasByTipo(TipoComida.BEBIDA).subscribe((data: Comida[]) => {
      this.bebidas = data;
    });
    this.comidaService.getComidasByTipo(TipoComida.PLATO_PRINCIPAL).subscribe((data: Comida[]) => {
      this.platosPrincipales = data;
    });
    this.comidaService.getComidasByTipo(TipoComida.POSTRE).subscribe((data: Comida[]) => {
      this.postres = data;
    });
  }

  get comida() {
    return this.menuForm.get('comida') as FormGroup;
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        this.menuRequest.foto = reader.result as string;
      };

      reader.readAsDataURL(file); 
    }
  }

  onSubmit(): void {
    if (this.menuForm.valid) {
      const { titulo, foto, precio, veggie, comidas } = this.menuForm.value;
      const { entrada, bebida, platoPrincipal, postre } = comidas;

      this.menuRequest.nombre = titulo;
      this.menuRequest.precio = precio;
      this.menuRequest.veggie = veggie;
      this.menuRequest.comidas = [
        entrada,
        bebida,
        platoPrincipal,
        postre
      ] 

      this.menuService.create(this.menuRequest).subscribe({
        next: (response) => {
          console.log('Menú agregado:', response);
          this.router.navigate(['/menu-list']);
          Swal.fire('Éxito', 'El menú se ha creado exitosamente.', 'success');
        },
        error: (error) => {
          console.error('Error al agregar el menú:', error);
          Swal.fire('Error', 'Hubo un problema al crear el menú.', 'error');
        }
      });
    } else {
      console.log('Formulario no válido');
    }
  }
}