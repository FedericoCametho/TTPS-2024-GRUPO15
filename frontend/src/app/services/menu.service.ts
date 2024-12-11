import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { listar, agregar, actualizar } from './config.json';
import { Menu } from '../model/carta/producto/menu';
import { Router } from '@angular/router';
import { environment as env } from '../model/environment/environment';
import { MenuRequest } from '../model/carta/producto/request/menuRequest';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  constructor(private http: HttpClient, private router: Router) { }

  // createMenu(menu: any): Observable<any> {
  //   return this.http.post(this.apiUrl, menu);
  // }

  // updateMenu(id: number, menu: any): Observable<any> {
  //   return this.http.put(`${this.apiUrl}/${id}`, menu);
  // }

  getMenus() {
    return this.http.get<Menu[]>(`${env.url}/menu${listar}`)
  }

  create(menu: MenuRequest) {
    return this.http.post<Menu>(`${env.url}/menu${agregar}`, menu);
  }

  update(menu: MenuRequest, id: number) {
    return this.http.put<Menu>(`${env.url}/menu${actualizar}/${id}`, menu);
  }

  getMenuById(id: number) {
    return this.http.get<Menu>(`${env.url}/menu${listar}/${id}`);
  }

  getMenuesComunes() {
    return this.http.get<Menu[]>(`${env.url}/menu${listar}Comunes`)
  }

  getMenuesVeggies() {
    return this.http.get<Menu[]>(`${env.url}/menu${listar}Veggie`)
  }

  // getMenuById(id: number): Observable<any> {
  //   return this.http.get<any>(`${this.apiUrl}/${id}`);
  // }
}
