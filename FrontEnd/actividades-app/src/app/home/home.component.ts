import { Component, OnInit } from '@angular/core';
import { StorageService } from '../service/storage.service';
import { HttpClient, HttpBackend } from '@angular/common/http'
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  private router: Router;
  private storageService: StorageService;
  private http: HttpClient;
  private userClient;
  private userImage;

  constructor(
    storageService: StorageService,
    router: Router,
    handler: HttpBackend
  ) { 
    this.storageService = storageService;
    this.router = router;
    this.http = new HttpClient(handler);
    this.userClient = null;
  }

  ngOnInit() {
    if(!this.storageService.isAuthenticated()){
      this.router.navigate(['/login']);
    }else{
      this.userName(this.storageService.getCurrentSession().token);
    }
  }

  logout() {
    this.storageService.logout();
  }

  userName (id_token) {
    var uri = 'https://oauth2.googleapis.com/tokeninfo?id_token=' + id_token;
    return this.http.get(uri).subscribe (
      Response => {
        this.userClient = Response['name'];
        this.userImage = Response['picture'];
      });
  }

}
