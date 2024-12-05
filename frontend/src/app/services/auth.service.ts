import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { registrar, login } from './config.json';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment as env } from '../model/environment/environment';
import { Usuario } from '../model/usuario/entity/usuario';

interface Credential {
  expirationInSec: number;
  token: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<Usuario | any>;
  public currentUser: Observable<Usuario | any>;

  constructor(private http: HttpClient) { 
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser') || ''));
    this.currentUser = this.currentUserSubject.asObservable();
  }
  public get currentUserValue(): Usuario {
    return this.currentUserSubject.value;
}

  register(userRequest: any, userType:string): Observable<any> {
    return this.http.post(`${env.url}/${userType}${registrar}`, userRequest);
  }

  login(emailAndPass: any, userType:string): Observable<any> {
    return this.http.post<Credential>(`${env.url}${login}/${userType}`, emailAndPass)
    .pipe(map(credential => {
      if (credential && credential.token) {
        localStorage.setItem('currentUser', JSON.stringify(credential));
        this.currentUserSubject.next(credential);
      }
      return credential;
    }));
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

}