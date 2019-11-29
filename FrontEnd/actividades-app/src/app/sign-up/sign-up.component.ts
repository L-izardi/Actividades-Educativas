import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, Form } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService, Toast } from 'ngx-toastr';
import { TcUserService } from 'src/app/service/tc-user.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
  providers:[
    TcUserService
  ]
})
export class SignUpComponent implements OnInit {
  
  private registerForm: FormGroup;
  private route: ActivatedRoute;
  private router:Router;
  private toastr: ToastrService;
  private formBuilder: FormBuilder;
  private loading = false;
  private submitted = false;
  private tcUserService: TcUserService;
  private storageService: StorageService;
  private response: any;

  constructor(
    formBuilder: FormBuilder,
    route: ActivatedRoute,
    router: Router,
    tcUserService: TcUserService,
    toastr: ToastrService,
    storageService: StorageService
  ) {
    this.route = route;
    this.router = router;
    this.storageService = storageService;
    this.toastr = toastr;
    this.formBuilder = formBuilder;
    this.tcUserService= tcUserService;

  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      imageUrl:[null],
      emailVerified:[false],
      provider:['local'],
      providerId:[null]
  });
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;
   
    if (this.registerForm.invalid) {
      return;
    }
    this.loading = true;
    this.tcUserService.signUp(this.registerForm.value).subscribe(
      Response =>{
        this.response= Response;
        console.log(this.response);
        this.toastr.success('Registro Completo');
        this.router.navigate(['/login']);
      },
      error => {
        this.toastr.error('Status: ' + error.error.status + ' Error: ' + error.error.error + ' Message: ' + error.error.message);
      });
  }

}
