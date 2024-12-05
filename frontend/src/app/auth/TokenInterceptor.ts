import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpClient } from '@angular/common/http';

@Injectable()
    export class TokenInterceptor implements HttpInterceptor {

        constructor(private http: HttpClient) {}
      
        intercept(req: HttpRequest<any>, next: HttpHandler) {

          let authReq: HttpRequest<any> = req.clone({
              setHeaders: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
              }
            });
          
          return next.handle(req);
        }
}