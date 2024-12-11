import { Component, OnInit } from '@angular/core';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

import { ComidaRequest } from '../model/carta/producto/request/comidaRequest';
import { ComidaService } from '../services/comida.service';
import { TipoComida } from '../model/carta/producto/tipo-comida.enum';

@Component({
  selector: 'app-comida-update',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './comida-update.component.html',
  styleUrls: ['./comida-update.component.css']
})
export class ComidaUpdateComponent implements OnInit {
  comidaForm: FormGroup;
  tiposComida = Object.values(TipoComida);
  comidaRequest: ComidaRequest = new ComidaRequest();
  comidaId: number;

  constructor(
    private fb: FormBuilder,
    private comidaService: ComidaService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.comidaForm = this.fb.group({
      nombre: ['', [Validators.required]],
      foto: [''], 
      tipoComida: ['', [Validators.required]],
      inMenu: [false],
      precio: ['', [Validators.required, Validators.min(0)]]
    });

    this.comidaId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.comidaService.getComidaById(this.comidaId).subscribe((comida) => {
      this.comidaForm.patchValue(comida);
    });
  }

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

      reader.readAsDataURL(file); 
    }
  }

  onSubmit(): void {
    if (this.comidaForm.valid) {
      const { nombre, tipoComida, inMenu, precio, foto } = this.comidaForm.value;
      const comida: ComidaRequest = {
        id: this.comidaId,
        nombre: nombre,
        tipoComida: tipoComida,
        inMenu: inMenu || false, 
        precio: precio,
        foto: foto || '' 
      };

      this.comidaService.update(comida, this.comidaId).subscribe({
        next: (response) => {
          console.log('Comida actualizada:', response);
          this.router.navigate(['/comida-list']);
          Swal.fire('Éxito', 'La comida se ha actualizado exitosamente.', 'success');
        },
        error: (error) => {
          console.error('Error al actualizar la comida:', error);
          Swal.fire('Error', 'Hubo un problema al actualizar la comida.', 'error');
        }
      });
    } else {
      console.log('Formulario no válido');
    }
  }
}