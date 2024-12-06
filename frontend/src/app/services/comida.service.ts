import { Injectable } from '@angular/core';
import { registrar, listar, actualizar } from './config.json';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Comida } from '../model/carta/producto/comida';
import { environment as env } from '../model/environment/environment';
import { TipoComida } from '../model/carta/producto/tipo-comida.enum';

@Injectable({
  providedIn: 'root'
})
export class ComidaService {

  constructor(private http: HttpClient, private router: Router) { }



  getComidas() {
    return this.http.get<Comida[]>(`${env.url}/comida${listar}`)
  }

  getComidasByTipo(tipo: TipoComida) {
    return this.http.get<Comida[]>(`${env.url}/comida${listar}PorTipo/${tipo}`)
  }

}
