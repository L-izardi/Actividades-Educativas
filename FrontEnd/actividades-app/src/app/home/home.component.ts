import { Component, OnInit } from '@angular/core';
import { StorageService } from '../service/storage.service';
import { HttpClient, HttpBackend } from '@angular/common/http'
import { Router } from '@angular/router';
import { TcUserService } from 'src/app/service/tc-user.service';
import { TcUser } from '../model/tc-user';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-root',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  private router: Router;
  private storageService: StorageService;
  private tcUserService : TcUserService;
  private toastr: ToastrService;
  private http: HttpClient;
  private tcUser:TcUser;
  private response: any;
  user  = new TcUser();
  

  constructor(
    storageService: StorageService,
    router: Router,
    handler: HttpBackend,
    toastr: ToastrService,
    tcUserService:TcUserService,
  ) { 
    this.storageService = storageService;
    this.router = router;
    this.http = new HttpClient(handler);
    this.toastr=toastr;
    this.tcUserService=tcUserService;
    
  }

  ngOnInit() {
    if(!this.storageService.isAuthenticated()){
      this.router.navigate(['/login']);
    }else{
      this.userName();
    }
  }

  logout() {
    this.storageService.logout();
  }

  userName () {
    this.tcUserService.getUserMe().subscribe(
      Response => {
        this.response = Response;
        if(this.response.emailVerified == false ) {
          this.user = this.response;
          this.toastr.success(this.response.message);
        }
       else {
            this.toastr.error(this.response.message);
          }   
        },
        error => {
          this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
        }
      );
    }
    
  goToCentros(){
    this.router.navigate(['/centro']);
  }
}
