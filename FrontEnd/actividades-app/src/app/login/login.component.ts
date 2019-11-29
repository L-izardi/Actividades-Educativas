import { Component, OnInit } from '@angular/core';
import { TcUserService } from '../service/tc-user.service';
import { User } from '../model/user';
import { ToastrService, Toast } from 'ngx-toastr';
import { Router } from '@angular/router';
import { StorageService } from '../service/storage.service';
import { SessionUser } from '../model/session-user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [TcUserService]
})
export class LoginComponent implements OnInit {

  user: any = new User();
  private toastr: ToastrService;
  private tcUserService: TcUserService;
  private router: Router;
  private response;
  errorMessage: any;
  private storageService: StorageService;


  constructor(
    router: Router,
    tcUserService: TcUserService,
    toastr: ToastrService,
    storageService: StorageService
  ) { 
    this.router = router;
    this.tcUserService = tcUserService;
    this.toastr = toastr;
    this.storageService = storageService;
  }

  ngOnInit() {
    if(this.storageService.isAuthenticated()){
      this.router.navigate(['/home']);
    } else {
      sessionStorage.clear();
      var route = this.router.parseUrl(this.router.url);
      this.oauth2(route);
    }
  }
  
  oauth2 (user) {
    if(user.queryParamMap.get('token')!=null){
      this.tcUserService.loginOauth2(user.queryParamMap.get('token')).subscribe(
        Response =>{
          this.response = Response;
          if (this.response.provider === 'google') {
            let tcUser = this.response.data;
            const loginData = new SessionUser();
            loginData.tcUser = tcUser;
            loginData.token = user.queryParamMap.get('token');
            this.storageService.setCurrentSession(loginData);
            sessionStorage.setItem('token', user.queryParamMap.get('token'));
            this.toastr.success('Acceso correcto');
            this.router.navigate(['/home']);
          }
        },error => {
          this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
        }
      )
    }
  }


  login() {
    this.tcUserService.login(this.user).subscribe (
      Response => {
        this.response = Response;
        if (this.response.status === 'ok') {
          let token = this.response.singleValue;
          let tcUser = this.response.data[0];
          const loginData = new SessionUser();
          loginData.tcUser = tcUser;
          loginData.token = token;
          this.storageService.setCurrentSession(loginData);
          sessionStorage.setItem('token', token);
          this.toastr.success('Acceso correcto');
          this.router.navigate(['/home']);
        } else {
          this.errorMessage = this.response.message;
          this.toastr.error(this.response.message);
        }
      }, 
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      }
    );   }

    
  goToSignUp(){
    this.router.navigate(['/registro']);
  }
  
}
