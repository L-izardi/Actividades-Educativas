import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpBackend } from '@angular/common/http'
import { User } from '../model/user';
import { Config } from './config';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public url: String;
  private http: HttpClient;

  constructor(
    handler: HttpBackend
  ) { 
    this.http = new HttpClient(handler);
    this.url = Config.apiUrl;
  }

  login(user: User) {
    return this.http.post(this.url + 'auth/login', user);
  }
  
  userMe(token: String ){
    let headers = new HttpHeaders();
    headers=headers.set('Content-Type','application/json; charset=utf-8');
    headers=headers.set('Authorization','Bearer '+token);
    return this.http.get(this.url+'user/me', {headers:headers});
  }
}
