import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DiaSemana } from '../model/carta/dia-semana.enum';
import { CartaDelDia } from '../model/carta/cartaDelDia';
import { CartaDelDiaService } from '../services/carta-del-dia.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  cartas: CartaDelDia[] = [];
  cartaDelDia: CartaDelDia | undefined;

  constructor(private cartaDelDiaService: CartaDelDiaService,) { }

  ngOnInit(): void {
    const diasSemana = ['domingo', 'LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'sabado'];
    const hoy = new Date();
    const diaHoy = diasSemana[hoy.getDay()];

    this.cartaDelDiaService.getCartaDelDia().subscribe((resp) => {
      this.cartas = resp.map(item => new CartaDelDia(
        item.id,
        item.menus || [],
        item.diaSemana as DiaSemana,
        item.activa,
      ));

      this.cartaDelDia = this.cartas.find(carta => carta.diaSemana === diaHoy);
    });


  }
}