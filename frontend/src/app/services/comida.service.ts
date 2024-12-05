import { Injectable } from '@angular/core';
import { baseUrl, registrar, listar, actualizar } from './config.json';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Comida } from '../model/carta/producto/comida';
import { environment as env } from '../model/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class ComidaService {

  constructor(private http: HttpClient, private router: Router) { }



  getComidas() {
    return this.http.get<Comida[]>(`${env.url}/comida${listar}`)
  }

}
