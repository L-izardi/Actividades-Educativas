import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './config';
import { TcTema } from '../model/tc-tema';
import { TcDetalle } from '../model/tc-detalle';

@Injectable({
  providedIn: 'root'
})
export class TcTemaService {

  private url: String;
  private http: HttpClient;
  constructor(
    http: HttpClient
  ) {
    this.http = http;
    this.url = Config.apiUrl;
   }

   addTema (data: TcTema){
     return this.http.post(this.url + 'tema/add', data);
   }
   updateTema(data: TcTema,id:number){
     return this.http.put(this.url + 'tema/'+id, data);
   }
   updateDetalle(data: TcDetalle, id:number){
    return this.http.put(this.url + 'tema/detalle/'+id, data);
  }
   getTemasAll(){
    return this.http.get(this.url + 'tema/all');
  }
   getTemas( id: number){
      return this.http.get(this.url + 'tema/'+ id);
   }
   getDetalle( id: number){
    return this.http.get(this.url + 'tema/detalle/'+ id);
 }

}
