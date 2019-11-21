import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './config';
import { TcHeaders } from '../model/tc-headers';

@Injectable({
  providedIn: 'root'
})
export class TcHeadersService {

  private url: String;
  private http: HttpClient;
  constructor(
    http: HttpClient
  ) {
    this.http = http;
    this.url = Config.apiUrl;
   }

   addHeaders (data: TcHeaders){
     return this.http.post(this.url + 'headers/add', data);
   }
   updateHeaders(data: TcHeaders,id:number){
     return this.http.put(this.url + 'headers/'+id, data);
   }
   getHeaderssAll(){
    return this.http.get(this.url + 'headers/all');
  }
   getHeaderss( id: number){
      return this.http.get(this.url + 'headers/'+ id);
   }

}
