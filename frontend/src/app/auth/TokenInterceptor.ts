import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import { Credential } from '../services/auth.service';


export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

        const authService = inject(AuthService);
      
        const currentUser:Credential = authService.currentUserValue;

        
        if (currentUser && currentUser.token) {
              
            const clonedReq = req.clone({
              setHeaders: {
                Authorization: `Bearer ${currentUser.token}`
              }
            });
            return next(clonedReq);
          }
          return next(req);
}
