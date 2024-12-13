import { Injectable } from '@angular/core';
import { agregar, listar, actualizar } from './config.json';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Comida } from '../model/carta/producto/comida';
import { environment as env } from '../model/environment/environment';
import { TipoComida } from '../model/carta/producto/tipo-comida.enum';
import { ComidaRequest } from '../model/carta/producto/request/comidaRequest';

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

  getComidaById(id: number) {
    return this.http.get<Comida>(`${env.url}/comida${listar}/${id}`)
  }

  createComida(comida: ComidaRequest) {
    return this.http.post(`${env.url}/comida${agregar}`, comida)
  }

  update(comida: ComidaRequest, id: number) {
    return this.http.put(`${env.url}/comida${actualizar}/${id}`, comida)
  }
}
