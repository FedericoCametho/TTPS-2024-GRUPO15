import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';

import { ReloadService } from '../services/reload.service';
import { CartaDelDiaService } from '../services/carta-del-dia.service';
import { MenuService } from '../services/menu.service';
import { Menu } from '../model/carta/producto/menu';
import { CartaDelDiaRequest } from '../model/carta/request/cartaDelDiaRequest';
import { DiaSemana } from '../model/carta/dia-semana.enum';

@Component({
  selector: 'app-carta-del-dia-update',
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './carta-del-dia-update.component.html',
  styleUrl: './carta-del-dia-update.component.css'
})
export class CartaDelDiaUpdateComponent implements OnInit {
  cartaForm: FormGroup;
  vegetarianMenus: any[] = [];
  commonMenus: any[] = [];
  diasSemana = Object.values(DiaSemana);
  cartaId: number;

  constructor(
    private fb: FormBuilder,
    private cartaDelDiaService: CartaDelDiaService,
    private menuService: MenuService,
    private route: ActivatedRoute,
    private router: Router
  ) { 
    this.cartaId = this.route.snapshot.params['id'];
    this.cartaForm = this.fb.group({
      menuVegetariano: [null, Validators.required],
      menuComun: [null, Validators.required],
      diaSemana: [null, Validators.required],
      activo: [false, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadMenus();
    this.loadCartaDelDia();
  }

  loadMenus(): void {
    this.menuService.getMenuesVeggies().subscribe(menus => {
      this.vegetarianMenus = menus;
    });

    this.menuService.getMenuesComunes().subscribe(menus => {
      this.commonMenus = menus;
    });
  }

  loadCartaDelDia(): void {
    this.cartaDelDiaService.getCartaDelDiaById(this.cartaId).subscribe(carta => {
      const menuVegetariano = carta.menus.find((menu: Menu) => menu.id !== undefined && this.isVegetarianMenu(menu.id));
      const menuComun = carta.menus.find((menu: Menu) => menu.id !== undefined && !this.isVegetarianMenu(menu.id));

      if (menuVegetariano?.id !== undefined && menuComun?.id !== undefined) {
        this.menuService.getMenuById(menuVegetariano.id).subscribe(menuVegetarianoDetalle => {
          this.menuService.getMenuById(menuComun.id).subscribe(menuComunDetalle => {
            this.cartaForm.patchValue({
              menuVegetariano: menuVegetarianoDetalle.id,
              menuComun: menuComunDetalle.id,
              diaSemana: carta.diaSemana,
              activo: carta.activa
            });
          });
        });
      }
    });
  }

  isVegetarianMenu(menuId: number): boolean {
    const menu = this.vegetarianMenus.find(menu => menu.id === menuId);
    return !!menu;
  }

  onSubmit(): void {
    if (this.cartaForm.valid) {
      const { menuVegetariano, menuComun, diaSemana, activo } = this.cartaForm.value;
      const menues = [menuVegetariano, menuComun];
      const cartaDelDiaRequest: CartaDelDiaRequest = { 
        menues: menues, 
        diaSemana: diaSemana, 
        isActiva:activo 
      };

      this.cartaDelDiaService.update(cartaDelDiaRequest, this.cartaId).subscribe({
        next: (response) => {
          Swal.fire({
            icon: 'success',
            title: 'Carta del día actualizada',
            text: 'La carta del día se ha actualizado correctamente.',
          }).then(() => {
            this.router.navigate(['/carta-del-dia-list']);
          });
        },
        error: (error) => {
          console.error('Error al actualizar la carta del día', error);
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Hubo un error al actualizar la carta del día.',
          });
        }
      });
    }
  }
}
