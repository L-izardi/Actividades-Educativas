import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/service/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  
  private storageService: StorageService;
  private router: Router;
  navbarOpen = false;

  constructor(storageService: StorageService, router: Router) {
    this.storageService = storageService;
    this.router = router;
   }

  ngOnInit() {
  }
  
  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }
  
  logout() {
    this.storageService.logout();
  }
  goToHome(){
    this.router.navigate(['/home']);
  }

  goToCurso(){
    this.router.navigate(['/cursos']);
  }
  goToCentro(){
    this.router.navigate(['/centro']);
  }
  goToCentroAdd(){
    this.router.navigate(['/centro/add']);
  }


  goToFacultad(){
    this.router.navigate(['/facultad']);
  }
  goToFacultadAdd(){
    this.router.navigate(['/facultad/add']);
  }
}
