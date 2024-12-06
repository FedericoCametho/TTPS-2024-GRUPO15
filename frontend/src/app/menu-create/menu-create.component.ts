import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MenuService } from '../services/menu.service';

import { ReactiveFormsModule } from '@angular/forms';;
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';


import { ComidaService } from '../services/comida.service';
import { TipoComida } from '../model/carta/producto/tipo-comida.enum';
import { MenuRequest } from '../model/carta/producto/request/menuRequest';


@Component({
  selector: 'app-menu-create',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './menu-create.component.html',
  styleUrl: './menu-create.component.css'
})
export class MenuCreateComponent {
  menuForm: FormGroup;
  entradas: any[] = [];
  bebidas: any[] = [];
  platosPrincipales: any[] = [];
  postres: any[] = [];
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
      esVegano: [false, [Validators.required]],
      comidas: this.fb.group({
        entrada: ['', [Validators.required]],
        bebida: ['', [Validators.required]],
        platoPrincipal: ['', [Validators.required]],
        postre: ['', [Validators.required]]
      })
    });
  }

  ngOnInit(): void {
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


  onSubmit(): void {
    if (this.menuForm.valid) {
      const { titulo, precio, esVegano, foto, comidas } = this.menuForm.value;
      const { entrada, bebida, platoPrincipal, postre } = comidas;
      const menu: MenuRequest = {
        id: 0, // El id se genera automáticamente en el backend
        nombre: titulo,
        precio: precio,
        veggie: esVegano,
        foto: foto,
        comidas: [
          entrada,
          bebida,
          platoPrincipal,
          postre
        ]
      };

      this.menuService.create(menu).subscribe(response => {
        console.log('Menú agregado:', response);
        Swal.fire({
          title: 'Éxito',
          text: 'El menú se ha creado exitosamente.',
          icon: 'success',
          confirmButtonText: 'OK'
        }).then(() => {
          this.router.navigate(['/menu-list']);
        });
      }, error => {
        console.error('Error al agregar el menú:', error);
        Swal.fire({
          title: 'Error',
          text: 'Hubo un problema al crear el menú.',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      });
    } else {
      console.log('Formulario no válido');
    }
  }
}