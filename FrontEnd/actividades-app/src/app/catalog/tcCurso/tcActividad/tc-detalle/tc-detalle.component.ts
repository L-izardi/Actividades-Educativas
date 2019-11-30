import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from 'src/app/service/storage.service';
import { ToastrService } from 'ngx-toastr';
import { TcTema } from 'src/app/model/tc-tema';
import { TcDetalle } from 'src/app/model/tc-detalle';
import { TcTemaService } from 'src/app/service/tc-tema.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-tc-detalle',
  templateUrl: './tc-detalle.component.html',
  styleUrls: ['./tc-detalle.component.scss']
})
export class TcDetalleComponent implements OnInit {

  private route: ActivatedRoute;
  private router: Router;
  private toastr: ToastrService;
  private response: any;
  private storageService: StorageService;
  private tcTemaService : TcTemaService;
  private modalService: NgbModal;
  generalForm: FormGroup;
  private formBuilder: FormBuilder;
  
  closeResult: string;


  tema : any = new TcTema();
  temas : TcTema[];
  detalle : TcDetalle[];

  constructor(
    route: ActivatedRoute,
    router: Router,
    storageService: StorageService,
    toastr: ToastrService,
    tcTemaService: TcTemaService,
    modalService: NgbModal,
    formBuilder: FormBuilder,
    private zone: NgZone
  ) {
    this.route = route;
    this.router = router;
    this.storageService = storageService;
    this.toastr = toastr;
    this.tcTemaService = tcTemaService;
    this.modalService = modalService;
    this.formBuilder = formBuilder;
   }

  ngOnInit() {
    this.generalForm = this.formBuilder.group({
      idCorrelativo:null,
      subtema: null,
      mes : null,
      fechaDesarrollar: null,
      fechaRevision : null,
      porcAvanceSemanal: null,
      porcAvanceMensual: null,
      estado:null
    });
   let idTema = this.route.snapshot.params['idTema'];
    this.tcTemaService.getTemas(idTema).subscribe(
      Response => {
        this.response = Response;
        if (this.response.status == 'ok') {
              this.temas = this.response.data;
              this.detalle= this.response.data[0].detalle;
              console.log(this.detalle);
        }
      },
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      });
  }

  actualizarAvance(item: TcDetalle, content){
    
    this.generalForm.get('idCorrelativo').setValue(item.idCorrelativo);
    this.generalForm.get('subtema').setValue(item.subtema);
    this.generalForm.get('mes').setValue(item.mes);
    this.generalForm.get('fechaDesarrollar').setValue(item.fechaDesarrollar);
    this.generalForm.get('fechaRevision').setValue(item.fechaRevision);
    this.generalForm.get('porcAvanceSemanal').setValue(item.porcAvanceSemanal);
    this.generalForm.get('porcAvanceMensual').setValue(item.porcAvanceMensual);
    this.generalForm.get('estado').setValue(item.estado);
    this.tema.idTema =  this.route.snapshot.params['idTema'];
    this.modalService.open(content);
    
  }
  onSubmit(){
    this.modalService.dismissAll();
    let data : TcDetalle = this.generalForm.value;
    data.tcTema= this.tema;
    this.tcTemaService.updateDetalle(data, data.idCorrelativo).subscribe(
      Response => {
        this.response = Response;
        if (this.response.status === 'ok') {          
          this.toastr.success(this.response.message);
          this.zone.runOutsideAngular(() => {
            location.reload();
        });
        } else {
          this.toastr.error(this.response.message);
        }
    },
    error => {
      this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
    });
  }
}
