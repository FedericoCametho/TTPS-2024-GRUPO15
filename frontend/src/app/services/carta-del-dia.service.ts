import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseUrl, listar, cartaDelDia, agregar, actualizar } from './config.json';
import { CartaDelDia } from '../model/carta/cartaDelDia';
import { Router } from '@angular/router';
import { DiaSemana } from '../model/carta/dia-semana.enum';
import { environment as env } from '../model/environment/environment';
import { CartaDelDiaRequest } from '../model/carta/request/cartaDelDiaRequest';


@Injectable({
  providedIn: 'root'
})
export class CartaDelDiaService {

  constructor(private http: HttpClient, private router: Router) { }

  getCartasDelDia() {
    return this.http.get<CartaDelDia[]>(`${env.url}${cartaDelDia}${listar}`)
  }

  getCartaDelDiaByDiaSemana(diaSemana: DiaSemana) {
    return this.http.get<CartaDelDia>(`${env.url}${cartaDelDia}${listar}Dia/${diaSemana}`)
  }

  create(carta: CartaDelDiaRequest) {
    return this.http.post(`${env.url}${cartaDelDia}${agregar}`, carta);
  }

  getCartaDelDiaById(id: number) {
    return this.http.get<CartaDelDia>(`${env.url}${cartaDelDia}${listar}/${id}`);
  }

  update(carta: CartaDelDiaRequest, id: number) {
    return this.http.put(`${env.url}${cartaDelDia}${actualizar}/${id}`, carta);
  }
}
