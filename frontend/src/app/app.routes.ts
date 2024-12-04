import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AlumnoRegistroComponent } from './register/alumno-register/alumno-register.component';
import { LoginComponent } from './login/login.component';
import { MenuCreateComponent } from './menu-create/menu-create.component';
import { MenuUpdateComponent } from './menu-update/menu-update.component';
import { MenuListComponent } from './menu-list/menu-list.component';
import { ComidaComponent } from './comida-list/comida.component';
import { HomeListMenuComponent } from './home-list-menu/home-list-menu.component';


export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'register-alumno', component: AlumnoRegistroComponent },
    { path: 'login', component: LoginComponent },
    { path: 'menu-create', component: MenuCreateComponent },
    { path: 'menu-update/:id', component: MenuUpdateComponent },
    { path: 'menu-list', component: MenuListComponent },
    { path: 'home-menu-list', component: HomeListMenuComponent },
    { path: 'comida-list', component: ComidaComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }