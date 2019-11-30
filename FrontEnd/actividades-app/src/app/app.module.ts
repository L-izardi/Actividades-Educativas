import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { NgSelectModule } from '@ng-select/ng-select';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AuthorizatedGuard } from './guard/authorizated.guard';
import { StorageService } from './service/storage.service';
import { DatePipe } from '@angular/common';
import { AuthInterceptorService } from './service/auth-interceptor.service';
import { TcCentroComponent } from './catalog/tcCentro/tc-centro/tc-centro.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { TcFacultadComponent } from './catalog/tcFacultad/tc-facultad/tc-facultad.component';
import { NavbarComponent } from './catalog/template/navbar/navbar.component';
import { FooterComponent } from './catalog/template/footer/footer.component';
import { TcCentroAddComponent } from './catalog/tcCentro/tc-centro-add/tc-centro-add.component';
import { TcCursoComponent } from './catalog/tcCurso/tc-curso/tc-curso.component';
import { TcFacultadAddComponent } from './catalog/tcFacultad/tc-facultad-add/tc-facultad-add.component';
import { TcTemaComponent } from './catalog/tcCurso/tcActividad/tc-tema/tc-tema.component';
import { TcDetalleComponent } from './catalog/tcCurso/tcActividad/tc-detalle/tc-detalle.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    TcCentroComponent,
    SignUpComponent,
    TcFacultadComponent,
    NavbarComponent,
    FooterComponent,
    TcCentroAddComponent,
    TcCursoComponent,
    TcFacultadAddComponent,
    TcTemaComponent,
    TcDetalleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right'
    }),
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    NgSelectModule
  ],
  providers: [
    AuthorizatedGuard,
    StorageService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi : true
    }, 
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
