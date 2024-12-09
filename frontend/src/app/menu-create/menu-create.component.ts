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
      foto: [''], // Campo opcional
      precio: ['', [Validators.required, Validators.min(0)]],
      veggie: [false, [Validators.required]],
      comidas: this.fb.group({ // Solo un grupo de controles para comida
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

      reader.readAsDataURL(file); // Leer el archivo como Base64
    }
  }

  onSubmit(): void {
    if (this.menuForm.valid) {
      const { titulo, foto, precio, veggie, comidas } = this.menuForm.value;
      const { entrada, bebida, platoPrincipal, postre } = comidas;
      const menuRequest: MenuRequest = {
        id: 0, // El id se genera automáticamente en el backend
        nombre: titulo,
        precio: precio,
        veggie: veggie,
        foto: foto || '', // Valor por defecto si es opcional
        comidas: [
          entrada,
          bebida,
          platoPrincipal,
          postre
        ] // Enviar como   un array con una sola comida
      };

      console.log(menuRequest);

      this.menuService.create(menuRequest).subscribe({
        next: (response) => {
          console.log('Menú agregado:', response);
          this.router.navigate(['/menu-list']); // Reemplaza '/ruta-deseada' con la ruta a la que deseas redirigir
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