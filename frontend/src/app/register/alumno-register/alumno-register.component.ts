import { Component } from '@angular/core';
import { AlumnoRequest } from './../../model/usuario/request/alumnoRequest';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-alumno-register',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './alumno-register.component.html',
  //styleUrls: ['./alumno-registro.component.css']
})
export class AlumnoRegistroComponent {
  alumnoRequest: AlumnoRequest = new AlumnoRequest();
  selectedFile: File | null = null;
  formulario: FormGroup;

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder) { 
    this.formulario = this.fb.group({
      email: ['', Validators.required],
      contrasena: ['', Validators.required],
      tipoUsuario: ['', Validators.required],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      dni: ['', Validators.required]
    });
    this.alumnoRequest.habilitado = true;
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        this.alumnoRequest.foto = reader.result as string || '';
      };

      reader.readAsDataURL(file); // Leer el archivo como Base64
    }
  }

  // Método para registrar al usuario
  register(): void {
    const userType = "alumno";
    const {nombre, apellido, dni, email, contrasena } = this.formulario.value;
    this.alumnoRequest.nombre = nombre;
    this.alumnoRequest.apellido = apellido;
    this.alumnoRequest.dni = dni;
    this.alumnoRequest.email = email;
    this.alumnoRequest.contrasena = contrasena;

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



 


