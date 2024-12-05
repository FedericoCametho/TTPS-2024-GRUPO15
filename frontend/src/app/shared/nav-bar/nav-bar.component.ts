import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LoginButtonsComponent } from '../login-buttons/login-buttons.component';


@Component({
  selector: 'app-nav-bar',
  imports: [RouterModule, LoginButtonsComponent],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {

}
