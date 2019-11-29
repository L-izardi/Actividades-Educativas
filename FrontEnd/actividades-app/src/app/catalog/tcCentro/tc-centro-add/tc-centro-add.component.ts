import { Component, OnInit } from '@angular/core';
import { TcCentro } from 'src/app/model/tc-centro';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/service/storage.service';
import { ToastrService } from 'ngx-toastr';
import { TcCentroService } from 'src/app/service/tc-centro.service';
import { TcDepartamentoService } from 'src/app/service/tc-departamento.service';
import { TcDepartamento } from 'src/app/model/tc-departamento';

@Component({
  selector: 'app-tc-centro-add',
  templateUrl: './tc-centro-add.component.html',
  styleUrls: ['./tc-centro-add.component.scss']
})
export class TcCentroAddComponent implements OnInit {

  private router: Router;
  private storageService: StorageService;
  private toastr: ToastrService;
  private tcCentroService : TcCentroService;
  private tcDepartamentoService: TcDepartamentoService;
  private response;
  tcCentro: any = new TcCentro();
  departamento : any = new TcDepartamento();
  departamentos : TcDepartamento[];
  errorMessage: any;

  constructor(
    router: Router,
    tcCentroService: TcCentroService,
    toastr: ToastrService,
    storageService: StorageService,
    tcDepartamentoService: TcDepartamentoService
  ) { 
    this.router = router;
    this.tcCentroService = tcCentroService;
    this.toastr = toastr;
    this.storageService = storageService;
    this.tcDepartamentoService= tcDepartamentoService;
  }

  ngOnInit() {
    this.tcDepartamentoService.getDepartamentoAll().subscribe(
      Response => {
        this.response = Response;
        if (this.response.status === 'ok') {
          this.departamentos = this.response.data;                    
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
    this.tcCentro.tc_departamento = this.departamento;
    this.tcCentroService.addCentro (this.tcCentro).subscribe(
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
  }
}
