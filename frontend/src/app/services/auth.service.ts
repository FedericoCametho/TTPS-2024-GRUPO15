import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {  registrar, login } from './config.json';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment as env } from '../model/environment/environment';
import { StorageService } from './storage.service';

export interface Credential {
  expirationInSec: number;
  token: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<Credential | any>;
  public currentUser: Observable<Credential | any>;

  constructor(private http: HttpClient, private storageService :StorageService) { 
    const currentStoredUser = this.storageService.getItem('currentUser');
    this.currentUserSubject = new BehaviorSubject<any>(currentStoredUser);
    this.currentUser = this.currentUserSubject.asObservable();
  }
  public get currentUserValue(): Credential {
    return this.currentUserSubject.value;
}

  register(userRequest: any, userType:string): Observable<any> {
    return this.http.post(`${env.url}/${userType}${registrar}`, userRequest);
  }

  login(emailAndPass: any, userType:string): Observable<any> {
    return this.http.post<Credential>(`${env.url}${login}/${userType}`, emailAndPass)
    .pipe(map(credential => {
      if (credential && credential.token) {
        this.storageService.setItem('currentUser', JSON.stringify(credential));
        this.currentUserSubject.next(credential);
      }
      return credential;
    }));
  }

  logout() {
    this.storageService.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

}