import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { StorageService } from 'src/app/service/storage.service';
import { TcCentroService } from 'src/app/service/tc-centro.service';
import { TcCentro } from 'src/app/model/tc-centro';

@Component({
  selector: 'app-tc-centro',
  templateUrl: './tc-centro.component.html',
  styleUrls: ['./tc-centro.component.scss'],
  providers: [
    TcCentroService, StorageService
  ]
})
export class TcCentroComponent implements OnInit {

  private route: ActivatedRoute;
  private router: Router;
  private toastr: ToastrService;
  private tcCentro:TcCentro;
  private response: any;
  private storageService: StorageService;
  private tcCentroService: TcCentroService;
  data: any;

  constructor(
    route: ActivatedRoute,
    router: Router,
    storageService: StorageService,
    toastr: ToastrService,
    tcCentroService: TcCentroService
  ) {
    this.route = route;
    this.router = router;
    this.storageService = storageService;
    this.toastr = toastr;
    this.tcCentroService = tcCentroService;
   }

  ngOnInit() {

    this.tcCentroService.getCentrosAll().subscribe(
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
