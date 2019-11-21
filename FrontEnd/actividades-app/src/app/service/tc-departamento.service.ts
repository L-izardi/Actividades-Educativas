import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './config';
import { TcDepartamento } from '../model/tc-departamento';

@Injectable({
  providedIn: 'root'
})
export class TcDepartamentoService {

  private url: String;
  private http: HttpClient;
  constructor(
    http: HttpClient
  ) {
    this.http = http;
    this.url = Config.apiUrl;
   }
  
   addDepartamento(data: TcDepartamento){
     return this.http.post(this.url +'departamento/add',data);
   }
   getDepartamentoAll(){
    return this.http.get(this.url + 'departamento/all');
    }
   getDepartamento( id: number){
      return this.http.get(this.url + 'departamento/'+ id);
    }

}