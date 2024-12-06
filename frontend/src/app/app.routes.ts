import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AlumnoRegistroComponent } from './register/alumno-register/alumno-register.component';
import { LoginComponent } from './login/login.component';
import { MenuCreateComponent } from './menu-create/menu-create.component';
import { MenuUpdateComponent } from './menu-update/menu-update.component';
import { MenuListComponent } from './menu-list/menu-list.component';
import { ComidaComponent } from './comida-list/comida.component';
import { ComidaCreateComponent } from './comida-create/comida-create.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'register-alumno', component: AlumnoRegistroComponent },
    { path: 'login', component: LoginComponent },
    { path: 'menu-create', component: MenuCreateComponent },
    { path: 'menu-update/:id', component: MenuUpdateComponent },
    { path: 'menu-list', component: MenuListComponent },
    { path: 'comida-list', component: ComidaComponent},
    { path: 'comida-create', component: ComidaCreateComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }