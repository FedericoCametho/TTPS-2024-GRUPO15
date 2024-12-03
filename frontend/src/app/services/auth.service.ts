import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { baseUrl, registrar, login } from './config.json';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  register(userRequest: any, userType:string): Observable<any> {
    return this.http.post(`${baseUrl}/${userType}${registrar}`, userRequest);
  }

  login(credentials: any, userType:string): Observable<any> {
    return this.http.post(`${baseUrl}/${userType}${login}`, credentials);
  }
}