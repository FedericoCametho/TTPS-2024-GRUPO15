import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [CommonModule,
    FormsModule],
  templateUrl: './login.component.html',
  //styleUrl: './login.component.css'
})
export class LoginComponent {
  user: any;

  constructor(private authService: AuthService, private router: Router) { 

  };

  login(): void {
    const loginRequest = {
      email: this.user.email,
      contrasena: this.user.contrasena,
    }
    const tipoUsuario = this.user.tipoUsuario;
    
    this.authService.login(loginRequest, tipoUsuario).subscribe(
      response => {
        console.log('User logged in successfully');
        this.router.navigate(['/home']);
      },
      error => {
        console.error('Error logging in user', error);
      }
    );
  }

}
