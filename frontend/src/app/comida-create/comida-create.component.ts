import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

import { ComidaRequest } from '../model/carta/producto/request/comidaRequest';
import { ComidaService } from '../services/comida.service';
import { TipoComida } from '../model/carta/producto/tipo-comida.enum';

@Component({
  selector: 'app-comida-create',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './comida-create.component.html',
  styleUrls: ['./comida-create.component.css']
})
export class ComidaCreateComponent {
  comidaForm: FormGroup;
  tiposComida = Object.values(TipoComida);
  comidaRequest: ComidaRequest = new ComidaRequest();

  constructor(
    private fb: FormBuilder,
    private comidaService: ComidaService,
    private router: Router
  ) {
    this.comidaForm = this.fb.group({
      nombre: ['', [Validators.required]],
      foto: [''], // Campo opcional
      tipoComida: ['', [Validators.required]],
      inMenu: [false], // Campo opcional
      precio: ['', [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void { }

  get nombre() { return this.comidaForm.get('nombre'); }
  get foto() { return this.comidaForm.get('foto'); }
  get tipoComida() { return this.comidaForm.get('tipoComida'); }
  get descripcion() { return this.comidaForm.get('descripcion'); }
  get precio() { return this.comidaForm.get('precio'); }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        this.comidaRequest.foto = reader.result as string;
      };

      reader.readAsDataURL(file); // Leer el archivo como Base64
    }
  }

  onSubmit(): void {
    if (this.comidaForm.valid) {
      const { nombre, tipoComida, inMenu, precio, foto } = this.comidaForm.value;
      const comida: ComidaRequest = {
        id: 0, // El id se genera automáticamente en el backend
        nombre: nombre,
        tipoComida: tipoComida,
        inMenu: inMenu || false, // Valor por defecto si es opcional
        precio: precio,
        foto: foto || '' // Valor por defecto si es opcional
      };

      this.comidaService.createComida(comida).subscribe({
        next: (response) => {
          console.log('Comida agregada:', response);
          this.router.navigate(['/comida-list']); // Reemplaza '/ruta-deseada' con la ruta a la que deseas redirigir
          Swal.fire('Éxito', 'La comida se ha creado exitosamente.', 'success');
        },
        error: (error) => {
          console.error('Error al agregar la comida:', error);
          Swal.fire('Error', 'Hubo un problema al crear la comida.', 'error');
        }
      });
    } else {
      console.log('Formulario no válido');
    }
  }
}