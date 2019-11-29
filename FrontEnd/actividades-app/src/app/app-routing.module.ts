import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthorizatedGuard } from './guard/authorizated.guard';
import { TcCentroComponent } from './catalog/tcCentro/tc-centro/tc-centro.component';
import { TcCentroAddComponent } from './catalog/tcCentro/tc-centro-add/tc-centro-add.component';
import { TcFacultadComponent } from './catalog/tcFacultad/tc-facultad/tc-facultad.component';
import { SignUpComponent } from 'src/app/sign-up/sign-up.component';
const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent},
  { path: 'registro', component: SignUpComponent},
  { path: 'home', component: HomeComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'centro', component:TcCentroComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'centro/add', component:TcCentroAddComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'facultad', component:TcFacultadComponent, canActivate: [ AuthorizatedGuard ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
