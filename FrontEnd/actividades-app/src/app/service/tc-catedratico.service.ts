import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './config';
import { TcCatedratico } from '../model/tc-catedratico';

@Injectable({
  providedIn: 'root'
})
export class TcCatedraticoService {

  private url: String;
  private http: HttpClient;

  constructor(
    http: HttpClient
  ) {
    this.http = http;
    this.url = Config.apiUrl;
   }
   
   addCatedratico (data: TcCatedratico){
     return this.http.post(this.url + 'catedratico/add', data);
   }
   updateCatedratico(data: TcCatedratico,id:number){
     return this.http.put(this.url + 'catedratico/'+id, data);
   }
   getCatedraticosAll(){
    return this.http.get(this.url + 'catedratico/all');
  }
   getCatedraticos( id: number){
      return this.http.get(this.url + 'catedratico/'+ id);
   }

}
 