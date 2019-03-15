import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { ThreadResponse } from '../interfaces/threads-response.interface';

const url = `${environment.apiUrl}/threads`;
const accessToken = `${environment.masterKey}`;

@Injectable({
  providedIn: 'root'
})
export class ThreadService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  getAll(): Observable<ThreadResponse> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': ''
      })
    };
    return this.http.get<ThreadResponse>(`${url}?access_token=${accessToken}` ,requestOptions);
  }

  
  deleteThread(id: string) {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };

    return this.http.delete(`${url}/${id}`, requestOptions);
  }
}
