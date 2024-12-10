import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AlumnoRegistroComponent } from './register/alumno-register/alumno-register.component';
import { LoginComponent } from './login/login.component';
import { MenuCreateComponent } from './menu-create/menu-create.component';
import { MenuUpdateComponent } from './menu-update/menu-update.component';
import { MenuListComponent } from './menu-list/menu-list.component';
import { ComidaListComponent } from './comida-list/comida-list.component';
import { ComidaCreateComponent } from './comida-create/comida-create.component';
import { ComidaUpdateComponent } from './comida-update/comida-update.component';
import { CartadeldiaCreateComponent } from './carta-del-dia-create/carta-del-dia-create.component';
import { CartaDelDiaListComponent } from './carta-del-dia-list/carta-del-dia-list.component';
import { CartaDelDiaUpdateComponent } from './carta-del-dia-update/carta-del-dia-update.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'register-alumno', component: AlumnoRegistroComponent },
    { path: 'login', component: LoginComponent },
    { path: 'menu-create', component: MenuCreateComponent },
    { path: 'menu-update/:id', component: MenuUpdateComponent },
    { path: 'menu-list', component: MenuListComponent },
    { path: 'comida-list', component: ComidaListComponent },
    { path: 'comida-create', component: ComidaCreateComponent },
    { path: 'comida-update/:id', component: ComidaUpdateComponent },
    { path: 'carta-del-dia-create', component: CartadeldiaCreateComponent },
    { path: 'carta-del-dia-list', component: CartaDelDiaListComponent },
    { path: 'carta-del-dia-update/:id', component: CartaDelDiaUpdateComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }