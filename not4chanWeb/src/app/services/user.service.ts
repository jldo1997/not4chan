import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { UserResponse } from '../interfaces/users-response.interface';
import { TokenDto } from '../model/dto/token.dto';
import { Ban } from '../model/dto/banOrder.dto';
import { UserDto } from '../model/dto/user.dto';
import { AdvUserDto } from '../model/dto/advancedUser.dto';

const url = `${environment.apiUrl}/users`;
const accessToken = `${environment.masterKey}`;

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

  banUser(id: string): Observable<UserResponse> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };

    const ban = new Ban(true);

    return this.http.put<UserResponse>(`${url}/ban/${id}`, ban, requestOptions);
  }

  unbanUser(id: string): Observable<UserResponse> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };

    const ban = new Ban(false);

    return this.http.put<UserResponse>(`${url}/ban/${id}`, ban, requestOptions);
  }

  delUser(id: string){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };

    return this.http.delete(`${url}/${id}`, requestOptions);
  }

  editUser(dto: UserDto, userId: string) {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };

    return this.http.put(`${url}/${userId}`, dto, requestOptions);
  }

  createUser(dto: AdvUserDto) {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': ''
      })
    };

    return this.http.post(`${url}?access_token=${accessToken}`, dto, requestOptions);
  }
}
