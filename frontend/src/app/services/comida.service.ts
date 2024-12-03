import { Injectable } from '@angular/core';
import { baseUrl, registrar, listar, actualizar } from './config.json';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import {Comida} from '../model/producto/comida';

@Injectable({
  providedIn: 'root'
})
export class ComidaService {

  constructor(private http: HttpClient, private router: Router) {}



  getComidas(){
    return this.http.get<Comida[]>(`${baseUrl}/comida${listar}`)
  }

}
