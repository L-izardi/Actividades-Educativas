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
    console.log(user);
    return this.http.post(this.url + 'auth/login', user);
  }
  loginOauth2(code){
    const headers = new HttpHeaders({'Content-Type':'application/json; charset=utf-8'});
    return this.http.post("https://oauth2.googleapis.com/token", {
      "code" : code,
      "client_id" : "124840980704-cpper58q9r06nud9ctl2kp4iu335cg1u.apps.googleusercontent.com",
      "client_secret" : "pJRB1nhqVhnwwdQpqL3eCXNA",
      "redirect_uri" : "http://localhost:4200/login",
      "grant_type" : "authorization_code"
      
    },{headers:headers});
  }
}
