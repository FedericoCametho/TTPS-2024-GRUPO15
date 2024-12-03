import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MenuDelDiaServicio } from '../../../services/menu-del-dia-servicio';
import { PlatoDelDia } from '../../../models/plato-del-dia/plato-del-dia';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  platos: PlatoDelDia[] = [];

  ngOnInit() {
    this.platos = MenuDelDiaServicio.platosDelDia;
  }
}
