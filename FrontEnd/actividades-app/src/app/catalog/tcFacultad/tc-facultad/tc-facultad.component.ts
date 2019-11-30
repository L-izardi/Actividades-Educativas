import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { StorageService } from 'src/app/service/storage.service';
import { TcFacultadService } from 'src/app/service/tc-facultad.service';

@Component({
  selector: 'app-tc-facultad',
  templateUrl: './tc-facultad.component.html',
  styleUrls: ['./tc-facultad.component.scss'],
  providers: [
    TcFacultadService, StorageService
  ]
})
export class TcFacultadComponent implements OnInit {

  private route: ActivatedRoute;
  private router: Router;
  private toastr: ToastrService;
  private response: any;
  private storageService: StorageService;
  private tcFacultadService: TcFacultadService;
  data: any;

  constructor(
    route: ActivatedRoute,
    router: Router,
    storageService: StorageService,
    toastr: ToastrService,
    tcFacultadService: TcFacultadService
  ) {
    this.route = route;
    this.router = router;
    this.storageService = storageService;
    this.toastr = toastr;
    this.tcFacultadService = tcFacultadService;
   }

  ngOnInit() {

    this.tcFacultadService.getFacultadesAll().subscribe(
      Response => {
        this.response = Response;
        if(this.response.status == 'ok'){
          this.data = this.response.data;
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
}
