import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Menu } from '../model/carta/producto/menu';
import { MenuService } from '../services/menu.service';
import { CommonModule } from '@angular/common';
import { Comida } from '../model/carta/producto/comida';
import { ReloadService } from '../services/reload.service'
import { AuthService } from '../services/auth.service'
import { error } from 'node:console';


@Component({
  selector: 'app-menu-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './menu-list.component.html',
  styleUrl: './menu-list.component.css'
})
export class MenuListComponent {
  menus: Menu[] = [];
  rol: string = '';
  mostrarBotonesAdmin: boolean;

  constructor(private menuService: MenuService, private reloadService: ReloadService, private authService: AuthService) {
    this.mostrarBotonesAdmin = this.authService.isLoggedAsAdmin();
   }

  mostrarModal = false;
  menuSeleccionado: any = null;

  verDetalles(menu: any) {
    this.menuSeleccionado = menu;
    this.mostrarModal = true;
  }

  cerrarModal() {
    this.mostrarModal = false;
    this.menuSeleccionado = null;
  }

  ngOnInit(): void {
    this.cargarDatosDelBack();
    this.authService.currentUser.subscribe(user => {
      if (user) {
        this.rol = user.rol;
      }
    });
    this.reloadService.reload$.subscribe(() => {this.cargarDatosDelBack();})
  }
  

  getComidaPorTipo(tipo: string): Comida | undefined {
    return this.menuSeleccionado?.comidas.find((c: Comida) => c.tipoComida === tipo);
  }

  buildImagesUrl(menus: Menu[]): void{
    menus.forEach((menu: Menu) => {
      menu.foto = `data:image/png;base64,${menu.foto}`;
    })
  };

  cargarDatosDelBack():void{
    this.menuService.getMenus().subscribe({
      next: (resp: Menu[]) => {
        this.buildImagesUrl(resp);
        this.menus = resp;
      },
      error: (err) => {
        console.error('Error loading menus', err);
      },
    });
  }


  
}
