import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginDto } from '../model/dto/login.dto';
import { LoginResponse } from '../interfaces/login-response.interface';

const url = `${environment.apiUrl}/auth`;
const accessToken = `${environment.masterKey}`;

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) {}

  doLogin(loginDto: LoginDto): Observable<LoginResponse> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ` + btoa(`${loginDto.email}:${loginDto.password}`),
        'Access-Control-Allow-Origin': ''
        
      })
    };

    return this.http.post<LoginResponse>(`${url}?access_token=${accessToken}`, null, requestOptions);
  }

  setLoginData(loginResponse: LoginResponse) {
    localStorage.setItem('token', loginResponse.token);
    localStorage.setItem('email', loginResponse.user.email);
    localStorage.setItem('id', loginResponse.user.id);
    localStorage.setItem('name', loginResponse.user.name);
    localStorage.setItem('picture', loginResponse.user.picture);
    localStorage.setItem('role', loginResponse.user.role);
  }

  getToken(): string {
    return localStorage.getItem('token');
  }


  logout() {
    localStorage.clear();
  }

}
