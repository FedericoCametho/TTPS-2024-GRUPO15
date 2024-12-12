import { Component, OnInit } from '@angular/core';
import { CartaDelDiaService } from '../services/carta-del-dia.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth.service'
import { RouterModule } from '@angular/router';
import { ReloadService } from '../services/reload.service';
import { CartaDelDia } from '../model/carta/cartaDelDia';



@Component({
  selector: 'app-carta-del-dia-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './carta-del-dia-list.component.html',
  styleUrl: './carta-del-dia-list.component.css'
})
export class CartaDelDiaListComponent implements OnInit {
  cartasDelDia: CartaDelDia[] = [];
  mostrarBotonesAdmin: boolean;

  constructor(private cartaDelDiaService: CartaDelDiaService, private reloadService: ReloadService, private authService: AuthService) {
    this.mostrarBotonesAdmin = this.authService.isLoggedAsAdmin();
   }

  ngOnInit(): void {
    this.cargarDatosDelBack();
    this.reloadService.reload$.subscribe(() => {
      this.cargarDatosDelBack();
    })
  }

  cargarDatosDelBack(): void {
    this.cartaDelDiaService.getCartasDelDia().subscribe(cartas => {
      this.cartasDelDia = cartas;
    });
  }
}
