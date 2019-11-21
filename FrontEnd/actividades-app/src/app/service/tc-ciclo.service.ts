import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './config';
import { TcCiclo } from '../model/tc-ciclo';

@Injectable({
  providedIn: 'root'
})
export class TcCicloService {

  private url: String;
  private http: HttpClient;
  constructor(
    http: HttpClient
  ) {
    this.http = http;
    this.url = Config.apiUrl;
   }

   addCiclo (data: TcCiclo){
     return this.http.post(this.url + 'ciclo/add', data);
   }
   getCiclosAll(){
    return this.http.get(this.url + 'ciclo/all');
  }
   getCiclos( id: number){
      return this.http.get(this.url + 'ciclo/'+ id);
   }

}
