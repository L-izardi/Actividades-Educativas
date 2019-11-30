import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { StorageService } from 'src/app/service/storage.service';
import { TcCursoService } from 'src/app/service/tc-curso.service';
import { TcFacultadService } from 'src/app/service/tc-facultad.service';
import { TcDepartamentoService } from 'src/app/service/tc-departamento.service';
import { TcCentroService } from 'src/app/service/tc-centro.service';
import { TcFacultad } from 'src/app/model/tc-facultad';
import { TcCentro } from 'src/app/model/tc-centro';
import { TcDepartamento } from 'src/app/model/tc-departamento';
import { TcCurso } from 'src/app/model/tc-curso';
import { TcCatedratico } from 'src/app/model/tc-catedratico';
import { TcCatedraticoService } from 'src/app/service/tc-catedratico.service';
import { TcHeadersService } from 'src/app/service/tc-headers.service';
import { TcHeaders } from 'src/app/model/tc-headers';

@Component({
  selector: 'app-tc-curso',
  templateUrl: './tc-curso.component.html',
  styleUrls: ['./tc-curso.component.scss']
})
export class TcCursoComponent implements OnInit {

  private route: ActivatedRoute;
  private router: Router;
  private toastr: ToastrService;
  private response: any;
  private storageService: StorageService;
  private tcCursoService: TcCursoService;
  private tcFacultadService: TcFacultadService;
  private tcCentroService: TcCentroService;
  private tcDepartamentoService: TcDepartamentoService;
  private tcCatedraticoService: TcCatedraticoService;
  private tcHeaderService: TcHeadersService;

  
  header: any = new TcHeaders();
  headers : TcHeaders[];

  curso: any = new TcCurso();
  cursos: TcCurso[];

  facultad: any = new TcFacultad();
  facultades: TcFacultad[];

  centro: any = new TcCentro();
  centros: TcCentro[];

  departamento: any = new TcDepartamento();
  departamentos: TcDepartamento[];

  catedratico: any = new TcCatedratico();
  catedraticos: TcCatedratico[];

  data: any;
  centroFilter: any = new Array();
  facultadFilter: any = new Array();
  cursosFilter: any = new Array();
  headerFilter: any = new Array();

  constructor(
    route: ActivatedRoute,
    router: Router,
    storageService: StorageService,
    toastr: ToastrService,
    tcCursoService: TcCursoService,
    tcFacultadService: TcFacultadService,
    tcCentroService: TcCentroService,
    tcDepartamentoService: TcDepartamentoService,
    tcCatedraticoService: TcCatedraticoService,
    tcHeaderService: TcHeadersService
  ) {
    this.route = route;
    this.router = router;
    this.storageService = storageService;
    this.toastr = toastr;
    this.tcCursoService = tcCursoService;
    this.tcFacultadService = tcFacultadService;
    this.tcCentroService = tcCentroService;
    this.tcDepartamentoService = tcDepartamentoService;
    this.tcCatedraticoService = tcCatedraticoService;
    this.tcHeaderService = tcHeaderService;

  }

  ngOnInit() {
    this.tcDepartamentoService.getDepartamentoAll().subscribe(
      Response => {
        this.response = Response;
        if (this.response.status == 'ok') {
          this.departamentos = this.response.data;
          this.toastr.success(this.response.message);
        } else {
          this.toastr.error(this.response.message);
        }
      },
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      }
    );

    this.tcCatedraticoService.getCatedraticosAll().subscribe(
      Response => {
        this.response = Response;
        if (this.response.status == 'ok') {
          this.catedraticos = this.response.data;
          this.toastr.success(this.response.message);
        } else {
          this.toastr.error(this.response.message);
        }
      },
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      }
    );

  }
  getSelectDepartamento() {
    this.centros = null;
    this.tcCentroService.getCentrosAll().subscribe(
      Response => {
        this.response = Response;
        if (this.response.status == 'ok') {
          for (let c of this.response.data) {
            if (c.tc_departamento.idDepto == this.departamento.idDepto) {
              this.centroFilter.push(c);
              this.centros = this.centroFilter;
            }
          }
          this.toastr.success(this.response.message);
        } else {
          this.toastr.error(this.response.message);
        }
      },
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      }
    );
  }

  getSelectCentro() {
    this.facultades = null;
    this.tcFacultadService.getFacultadesAll().subscribe(
      Response => {
        this.response = Response;
        if (this.response.status == 'ok') {
          for (let f of this.response.data) {
            if (f.tc_centro.idCentro == this.centro.idCentro) {
              this.facultadFilter.push(f);
              this.facultades = this.facultadFilter;
            }
          }
        } else {
          this.toastr.error(this.response.message);
        }
      },
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      }
    );
  }

  getCursos() {
    this.tcCursoService.getCursosAll().subscribe(
      Response => {
        this.response = Response;
        if (this.response.status == 'ok') {
          for (let c of this.response.data) {
            if (c.tc_facultad.idFacultad == this.facultad.idFacultad) {
              this.cursosFilter.push(c);
              this.cursos = this.cursosFilter;
            }
          }
        } else {
          this.toastr.error(this.response.message);
        }
      },
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      }
    )
  }

  getHeader() {
    this.headers= null;
    this.headerFilter.length = 0;
    this.tcHeaderService.getHeaderssAll().subscribe(
      Response => {
        this.response = Response;
        if (this.response.status == 'ok') {
          console.log(this.response.data);
          for (let c of this.response.data) {
            if (c.tc_catedratico.idCatedratico == this.catedratico.idCatedratico && c.tc_curso.idCurso == this.curso.idCurso ) {
              this.headerFilter.push(c);
              this.headers= this.headerFilter;
              console.log(c)
            }
          }
        } else {
          this.toastr.error(this.response.message);
        }
      },
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      }
    )
  }

  actividades(item: TcHeaders){
    this.router.navigate(['/curso/'+item.idCorrelativo+'/actividad']);
  }
}
