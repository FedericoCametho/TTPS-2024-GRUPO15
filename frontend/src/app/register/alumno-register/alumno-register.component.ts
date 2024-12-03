import { Component } from '@angular/core';
import { AlumnoRequest } from './../../model/usuario/request/alumnoRequest';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-alumno-registro',
  templateUrl: './alumno-registro.component.html',
  //styleUrls: ['./alumno-registro.component.css']
})
export class AlumnoRegistroComponent {
  alumnoRequest: AlumnoRequest = new AlumnoRequest();
  selectedFile: File | null = null;

  constructor(private authService: AuthService, private router: Router) { 
    this.alumnoRequest.habilitado = true;
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        this.alumnoRequest.foto = reader.result as string;
      };

      reader.readAsDataURL(file); // Leer el archivo como Base64
    }
  }

  // Método para registrar al usuario
  register(): void {
    const userType = "alumno";
    const alumnoRequest = {
      nombre: this.alumnoRequest.nombre,
      apellido: this.alumnoRequest.apellido,
      dni: this.alumnoRequest.dni,
      email: this.alumnoRequest.email,
      contrasena: this.alumnoRequest.contrasena,
      foto: this.alumnoRequest.foto || null // Enviar la foto en formato Base64 o null si no hay
    };

    console.log('Datos del alumno a registrar:', this.alumnoRequest);
    this.authService.register(this.alumnoRequest, userType).subscribe(
      response => {
        console.log('User registered successfully');
        this.router.navigate(['/login']);
      },
      error => {
        console.error('Error registering user', error);
      }
    );
  }
}



 


