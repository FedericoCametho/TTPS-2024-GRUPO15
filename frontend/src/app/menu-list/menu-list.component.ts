import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Menu } from '../model/carta/producto/menu';
import { MenuService } from '../services/menu.service';
import { CommonModule } from '@angular/common';
import { Comida } from '../model/carta/producto/comida';
import { ReloadService } from '../services/reload.service'


@Component({
  selector: 'app-menu-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './menu-list.component.html',
  styleUrl: './menu-list.component.css'
})
export class MenuListComponent {
  menus: Menu[] = [];

  constructor(private menuService: MenuService, private reloadService: ReloadService) { }

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

    this.reloadService.reload$.subscribe(() => {this.cargarDatosDelBack();})
  }
  

  getComidaPorTipo(tipo: string): Comida | undefined {
    return this.menuSeleccionado?.comidas.find((c: Comida) => c.tipoComida === tipo);
  }


  cargarDatosDelBack():void{
    this.menuService.getMenus().subscribe((resp) => {
      this.menus = resp;
    });
  }
}
