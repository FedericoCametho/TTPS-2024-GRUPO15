import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { baseUrl, listar} from './config.json';
import { Menu } from '../model/carta/producto/menu';
import { Router } from '@angular/router';

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
    return this.http.get<Menu[]>(`${baseUrl}/menu${listar}`)
  }

  // getMenuById(id: number): Observable<any> {
  //   return this.http.get<any>(`${this.apiUrl}/${id}`);
  // }
}