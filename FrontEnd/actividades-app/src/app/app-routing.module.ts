import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthorizatedGuard } from './guard/authorizated.guard';
import { TcCentroComponent } from './catalog/tcCentro/tc-centro/tc-centro.component';
import { TcCentroAddComponent } from './catalog/tcCentro/tc-centro-add/tc-centro-add.component';
import { TcFacultadComponent } from './catalog/tcFacultad/tc-facultad/tc-facultad.component';
import { SignUpComponent } from 'src/app/sign-up/sign-up.component';
import { TcFacultadAddComponent } from './catalog/tcFacultad/tc-facultad-add/tc-facultad-add.component';
import { TcCursoComponent } from './catalog/tcCurso/tc-curso/tc-curso.component';
import { TcTemaComponent } from './catalog/tcCurso/tcActividad/tc-tema/tc-tema.component';
import { TcDetalleComponent } from './catalog/tcCurso/tcActividad/tc-detalle/tc-detalle.component';
const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent},
  { path: 'registro', component: SignUpComponent},
  { path: 'home', component: HomeComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'centro', component:TcCentroComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'centro/add', component:TcCentroAddComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'facultad', component:TcFacultadComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'facultad/add', component:TcFacultadAddComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'cursos', component:TcCursoComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'curso/:idCorrelativo/actividad', component:TcTemaComponent, canActivate: [ AuthorizatedGuard ]},
  { path: 'tema/:idTema/actividad', component:TcDetalleComponent, canActivate: [ AuthorizatedGuard ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
