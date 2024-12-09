import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import { Credential } from '../services/auth.service';


export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

        const authService = inject(AuthService);
      
        const currentUser:Credential = authService.currentUserValue;
        
        if (currentUser && currentUser.token) {
            let headers = req.headers.set('Authorization', `Bearer ${currentUser.token}`);
            headers.set('Access-Control-Allow-Origin', '*');
              
            const clonedReq = req.clone({
              headers
            });
            return next(clonedReq);
          }
          return next(req);
}
