import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './config';
import { TcCentro } from '../model/tc-centro';

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

   addCentro (data: TcCentro){
     return this.http.post(this.url + 'centro/add', data);
   }
   updateCentro(data: TcCentro,id:number){
     return this.http.put(this.url + 'centro/'+id, data);
   }
   getCentrosAll(){
    return this.http.get(this.url + 'centro/all');
  }
   getCentros( id: number){
      return this.http.get(this.url + 'centro/'+ id);
   }

}
