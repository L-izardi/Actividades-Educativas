import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpBackend } from '@angular/common/http'
import { User } from '../model/user';
import { Config } from './config';


@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  public url: String;
  private http: HttpClient;

  constructor(
    handler: HttpBackend
  ) { 
    this.http = new HttpClient(handler);
    this.url = Config.apiUrl;
  }

  signUp(user: User) {
    console.log(user);
    return this.http.post(this.url + 'auth/signup', user);
  }
}
