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

  constructor(private cartaDelDiaService: CartaDelDiaService) { }

  ngOnInit(): void {
    const hoy = new Date();
    const diaSemanaMap: { [key: number]: DiaSemana } = {
      1: DiaSemana.LUNES,
      2: DiaSemana.MARTES,
      3: DiaSemana.MIERCOLES,
      4: DiaSemana.JUEVES,
      5: DiaSemana.VIERNES,
    };
    const diaHoy: DiaSemana = diaSemanaMap[hoy.getDay()];

    this.cartaDelDiaService.getCartaDelDiaByDiaSemana(diaHoy).subscribe((resp) => {
      this.cartaDelDia = resp;  
    });

    // this.cartaDelDiaService.getCartaDelDia().subscribe((resp) => {
    //   this.cartas = resp.map(item => new CartaDelDia(
    //     item.id,
    //     item.menus || [],
    //     item.diaSemana as DiaSemana,
    //     item.activa,
    //   ));

    //   this.cartaDelDia = this.cartas.find(carta => carta.diaSemana === diaHoy);
    // });
  }
}