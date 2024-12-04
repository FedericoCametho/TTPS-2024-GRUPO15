import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Menu } from '../model/carta/producto/menu';
import { MenuService } from '../services/menu.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home-list-menu',
  imports: [CommonModule, RouterModule],
  templateUrl: './home-list-menu.component.html',
  styleUrl: './home-list-menu.component.css'
})
export class HomeListMenuComponent {
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
