import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './config';
import { TcFacultad } from '../model/tc-facultad';

@Injectable({
  providedIn: 'root'
})
export class TcFacultadService {

  private url: String;
  private http: HttpClient;
  constructor(
    http: HttpClient
  ) {
    this.http = http;
    this.url = Config.apiUrl;
   }

  addFacultad(data:TcFacultad){
    return this.http.post(this.url + '/api/Facultads/', data);
  }
  updateFacultad(data:TcFacultad, id:number){
    return this.http.put(this.url +'/api/Facultads/'+ id, data);
  }
  getFacultad(id:number){
    return this.http.get(this.url + '/api/Facultads/'+ id );
  }
  getFacultades(){
    return this.http.get(this.url + '/api/Facultads');
  }
}
