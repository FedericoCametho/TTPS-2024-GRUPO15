import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

import { CartaDelDiaService } from '../services/carta-del-dia.service';
import { MenuService } from '../services/menu.service';
import { DiaSemana } from '../model/carta/dia-semana.enum';
import Swal from 'sweetalert2';

import { CartaDelDiaRequest } from '../model/carta/request/cartaDelDiaRequest';

@Component({
  selector: 'app-carta-del-dia-create',
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './carta-del-dia-create.component.html',
  styleUrl: './carta-del-dia-create.component.css'
})
export class CartadeldiaCreateComponent  {
  cartaForm: FormGroup;
  vegetarianMenus: any[] = [];
  commonMenus: any[] = [];
  diasSemana = Object.values(DiaSemana);
  cartaDelDiaRequest: CartaDelDiaRequest = new CartaDelDiaRequest();

  constructor(
    private fb: FormBuilder,
    private cartaDelDiaService: CartaDelDiaService,
    private menuService: MenuService,
    private router: Router
  ) {
    this.cartaForm = this.fb.group({
      menuVegetariano: [null, Validators.required],
      menuComun: [null, Validators.required],
      diaSemana: [null, Validators.required],
      activo: [false, Validators.required]
    });
   }

  ngOnInit(): void {
    this.loadMenus();
  }

  loadMenus(): void {
    this.menuService.getMenuesVeggies().subscribe(menus => {
      this.vegetarianMenus = menus;
    });

    this.menuService.getMenuesComunes().subscribe(menus => {
      this.commonMenus = menus;
    });
  }

  onSubmit(): void {
    if (this.cartaForm.valid) {
      const { menuVegetariano, menuComun, diaSemana, activo } = this.cartaForm.value;
      const menues = [menuVegetariano, menuComun];
      const cartaDelDiaRequest: CartaDelDiaRequest = {
        menues : menues,
        diaSemana : diaSemana,
        isActiva : activo
      };

      console.log(cartaDelDiaRequest)

      this.cartaDelDiaService.create(cartaDelDiaRequest).subscribe({
        next: (response) => {
          Swal.fire({
            icon: 'success',
            title: 'Carta del día creada',
            text: 'La carta del día se ha creado correctamente.',
          }).then(() => {
            this.router.navigate(['/carta-del-dia-list']);
          });
        },
        error: (error) => {
          console.error('Error al crear la carta del día', error);
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Hubo un error al crear la carta del día.',
          });
        }
      });
    }
  }
}