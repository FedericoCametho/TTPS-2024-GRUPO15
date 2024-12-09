import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LoginButtonsComponent } from '../login-buttons/login-buttons.component';
import { UserButtonsComponent } from '../user-buttons/user-buttons.component';
import { StorageService } from '../../services/storage.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-nav-bar',
  imports: [RouterModule, LoginButtonsComponent, UserButtonsComponent],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  mostrarComponente: boolean;

  constructor(private authService: AuthService, private storageService: StorageService) { 
    this.mostrarComponente = this.storageService.getItem('currentUser') ? true : false;
  }

  ngOnInit(): void {
    this.authService.currentUser.subscribe(user => {
      this.mostrarComponente = !!user;
    });
  }
}