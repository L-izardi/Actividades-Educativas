import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { StorageService } from 'src/app/service/storage.service';
import { TcHeadersService } from 'src/app/service/tc-headers.service';
import { TcTemaService } from 'src/app/service/tc-tema.service';
import { TcTema } from 'src/app/model/tc-tema';
import { TcHeaders } from 'src/app/model/tc-headers';

@Component({
  selector: 'app-tc-tema',
  templateUrl: './tc-tema.component.html',
  styleUrls: ['./tc-tema.component.scss']
})
export class TcTemaComponent implements OnInit {

  private route: ActivatedRoute;
  private router: Router;
  private toastr: ToastrService;
  private response: any;
  private storageService: StorageService;
  private tcHeaderService: TcHeadersService;
  private tcTemaService: TcTemaService;
  
  header: any = new TcHeaders();
  tema: any = new TcTema();
  temas : TcTema[];
  temaFilter: any = new Array();

  constructor(
    route: ActivatedRoute,
    router: Router,
    storageService: StorageService,
    toastr: ToastrService,
    tcHeaderService: TcHeadersService,
    tcTemaService: TcTemaService
  ) {
    this.route = route;
    this.router = router;
    this.storageService = storageService;
    this.toastr = toastr;
    this.tcHeaderService = tcHeaderService;
    this.tcTemaService=tcTemaService;
   }

  ngOnInit() {
    this.header.idCorrelativo = this.route.snapshot.params['idCorrelativo'];
    this.tcTemaService.getTemasAll().subscribe(
      Response => {
        this.response = Response;
        if (this.response.status === 'ok') {
          for (let h of this.response.data) {
            if (h.tcHeaders.idCorrelativo == this.header.idCorrelativo) {
              this.temaFilter.push(h);
              this.temas = this.temaFilter;              
            }
          }
        }
      },
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      });
  }

  detalle(item: TcTema){
    this.router.navigate(['/tema/'+item.idTema+'/actividad']);
  }
}
