import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './config';
import { TcCurso } from '../model/tc-curso';

@Injectable({
  providedIn: 'root'
})
export class TcCursoService {

  private url: String;
  private http: HttpClient;
  constructor(
    http: HttpClient
  ) {
    this.http = http;
    this.url = Config.apiUrl;
   }

  addCurso(data:TcCurso){
    return this.http.post(this.url + 'curso/add', data);
  }
  updateCurso(data:TcCurso, id:number){
    return this.http.put(this.url + 'curso/'+ id, data);
  }
  getCursosAll(){
    return this.http.get(this.url + 'curso/all');
  }
  getCurso(id:number){
    return this.http.get(this.url + 'curso/'+ id );
  }   
}
