import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Menu } from '../model/carta/producto/menu';
import { MenuService } from '../services/menu.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-menu-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './menu-list.component.html',
  styleUrl: './menu-list.component.css'
})
export class MenuListComponent {
  menus: Menu[] = [];

  constructor(private menuService: MenuService) { }

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
    this.menuService.getMenus().subscribe((resp) => {
      this.menus = resp;
    })
  }
}
