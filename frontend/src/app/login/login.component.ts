import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [CommonModule,
    FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  //styleUrl: './login.component.css'
})
export class LoginComponent {
  formulario: FormGroup

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder) { 
      this.formulario = this.fb.group({
      email: ['', Validators.required],
      contrasena: ['', Validators.required],
      tipoUsuario: ['', Validators.required]
    });
  }

  login(): void {
    const {email, contrasena, tipoUsuario } = this.formulario.value;
    
    this.authService.login({email:email, contrasena:contrasena}, tipoUsuario.toLowerCase()).subscribe(
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
