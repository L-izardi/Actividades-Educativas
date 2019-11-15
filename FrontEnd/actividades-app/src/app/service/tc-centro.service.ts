import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './config';
import { TcCurso } from '../model/tc-curso';

@Injectable({
  providedIn: 'root'
})
export class TcCentroService {

  private url: String;
  private http: HttpClient;
  constructor(
    http: HttpClient
  ) {
    this.http = http;
    this.url = Config.apiUrl;
   }

   addCentro (data: TcCurso){
     return this.http.post(this.url + 'api/Centroes', data);
   }
   updateCentro(data: TcCurso,id:number){
     return this.http.put(this.url + 'api/Centroes/'+id, data);
   }
   getCentrosAll(){
    return this.http.get(this.url + 'api/Centroes');
  }
   getCentros( id: number){
      return this.http.get(this.url + 'api/Centroes/'+ id);
   }

}
