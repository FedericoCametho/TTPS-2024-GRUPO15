import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-buttons',
  imports: [],
  templateUrl: './user-buttons.component.html',
  styleUrl: './user-buttons.component.css'
})

export class UserButtonsComponent {
  email: string = '';
  rol: string = '';
  constructor(private authService: AuthService, private router: Router) { 
    this.authService.currentUser.subscribe(user => {
      if (user) {
        this.email = user.email;
        this.rol = user.rol;
      }
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}