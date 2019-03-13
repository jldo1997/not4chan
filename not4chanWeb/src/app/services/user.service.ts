import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { UserResponse } from '../interfaces/users-response.interface';
import { TokenDto } from '../model/dto/token.dto';

const url = `${environment.apiUrl}/users`;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  getAll(): Observable<UserResponse> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };
    return this.http.get<UserResponse>(`${url}` ,requestOptions);
  }
}
