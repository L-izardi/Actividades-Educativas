import { Component, OnInit } from '@angular/core';
import { TcFacultad } from 'src/app/model/tc-facultad';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/service/storage.service';
import { ToastrService } from 'ngx-toastr';
import { TcFacultadService } from 'src/app/service/tc-facultad.service';
import { TcCentroService } from 'src/app/service/tc-centro.service';
import { TcCentro } from 'src/app/model/tc-centro';

@Component({
  selector: 'app-tc-facultad-add',
  templateUrl: './tc-facultad-add.component.html',
  styleUrls: ['./tc-facultad-add.component.scss']
})
export class TcFacultadAddComponent implements OnInit {

  private router: Router;
  private storageService: StorageService;
  private toastr: ToastrService;
  private tcFacultadService : TcFacultadService;
  private tcCentroService: TcCentroService;
  private response;
  tcFacultad: any = new TcFacultad();
  centro : any = new TcCentro();
  centros : TcCentro[];
  errorMessage: any;

  constructor(
    router: Router,
    tcFacultadService: TcFacultadService,
    toastr: ToastrService,
    storageService: StorageService,
    tcCentroService: TcCentroService
  ) { 
    this.router = router;
    this.tcFacultadService = tcFacultadService;
    this.toastr = toastr;
    this.storageService = storageService;
    this.tcCentroService= tcCentroService;
  }

  ngOnInit() {
    this.tcCentroService.getCentrosAll().subscribe(
      Response => {
        this.response = Response;
        if (this.response.status === 'ok') {
          this.centros = this.response.data;                    
          this.toastr.success(this.response.message);
        } else {
          this.toastr.error(this.response.message);
        }
    },
    error => {
      this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
    });
  }

  add(){
    this.tcFacultad.tc_centro = this.centro;
    this.tcFacultadService.addFacultad (this.tcFacultad).subscribe(
      Response => { this.response = Response
      if (this.response.status === 'ok') {
        this.toastr.success(this.response.message);
      } else {
        this.toastr.error(this.response.message);
      }
    },
    error => {
      this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
    }); 
  
    console.log(this.tcFacultad);
}
}
