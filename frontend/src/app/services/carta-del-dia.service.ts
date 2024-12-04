import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseUrl, listar } from './config.json';
import { CartaDelDia } from '../model/carta/cartaDelDia';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CartaDelDiaService {

  constructor(private http: HttpClient, private router: Router) { }

  getCartaDelDia() {
    return this.http.get<CartaDelDia[]>(`${baseUrl}/cartaDelDia${listar}`)
  }

}
