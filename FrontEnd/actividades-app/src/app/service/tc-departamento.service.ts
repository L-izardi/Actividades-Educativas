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
     return this.http.post(this.url +'/api/Departamentoes/',data);
   }
   updateDepartamento(data: TcDepartamento, id:number){
     return this.http.put(this.url + '/api/Departamentoes/ ' +id, data );
    }
   getDepartamentoAll(){
    return this.http.get(this.url + 'api/Departamentoes');
    }
   getDepartamento( id: number){
      return this.http.get(this.url + 'api/Departamentoes/'+ id);
    }

}