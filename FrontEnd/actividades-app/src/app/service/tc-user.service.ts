import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Config } from './config';
import { TcUser } from '../model/tc-user';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class TcUserService {

  private url: String;
  private http: HttpClient;

  constructor(
    http: HttpClient
  ) {
    this.http = http;
    this.url = Config.apiUrl;
   }
   
   getUserMe(){
    return this.http.get(this.url + 'user/me');
  }
  
  login(user: User) {
    return this.http.post(this.url + 'auth/login', user);
  }
  
  loginOauth2(token: String ){
    let headers = new HttpHeaders();
    headers=headers.set('Content-Type','application/json; charset=utf-8');
    headers=headers.set('Authorization','Bearer '+token);
    return this.http.get(this.url+'user/me', {headers:headers});
  }

  signUp(user: User) {
    console.log(user);
    return this.http.post(this.url + 'auth/signup', user);
  }
}
 