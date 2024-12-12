import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReloadService } from '../services/reload.service';
import { AuthService } from '../services/auth.service';
import { ComidaService } from '../services/comida.service';
import { Comida } from '../model/carta/producto/comida';

@Component({
  selector: 'app-comida-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './comida-list.component.html',
  styleUrl: './comida-list.component.css'
})
export class ComidaListComponent {
  comidas: Comida[] = [];
  mostrarBotonesAdmin: boolean;

  constructor(private comidaService: ComidaService, private reloadService: ReloadService, private authService: AuthService) {
    this.mostrarBotonesAdmin = this.authService.isLoggedAsAdmin();
   }

  ngOnInit(): void {
    
    this.cargarDatosDelBack();
    this.reloadService.reload$.subscribe(() => {this.cargarDatosDelBack();})
  }


  cargarDatosDelBack(): void {
    this.comidaService.getComidas().subscribe((resp) => {
      this.comidas = resp;
    });
  }
}
