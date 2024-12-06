import { Injectable } from '@angular/core';
import { baseUrl, agregar, listar, actualizar } from './config.json';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Comida } from '../model/carta/producto/comida';
import { TipoComida } from '../model/carta/producto/tipo-comida.enum';
import { ComidaRequest } from '../model/carta/producto/request/comidaRequest';

@Injectable({
  providedIn: 'root'
})
export class ComidaService {

  constructor(private http: HttpClient, private router: Router) { }



  getComidas() {
    return this.http.get<Comida[]>(`${baseUrl}/comida${listar}`)
  }

  getComidasByTipo(tipo: TipoComida) {
    return this.http.get<Comida[]>(`${baseUrl}/comida${listar}PorTipo/${tipo}`)
  }

  getComidaById(id: number) {
    return this.http.get<Comida>(`${baseUrl}/comida${listar}/${id}`)
  }

  createComida(comida: ComidaRequest) {
    return this.http.post(`${baseUrl}/comida${agregar}`, comida)
  }

  update(comida: ComidaRequest, id: number) {
    return this.http.put(`${baseUrl}/comida${actualizar}/${id}`, comida)
  }

}
