import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { CommentResponse } from '../interfaces/comments-response.interface';
import { Observable } from 'rxjs';
import { CommentDto } from '../model/dto/comment.dto';

const url = `${environment.apiUrl}/comments`;
const accessToken = `${environment.masterKey}`;

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  getAll(): Observable<CommentResponse> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': ''
      })
    };
    return this.http.get<CommentResponse>(`${url}?access_token=${accessToken}` ,requestOptions);
  }

  deleteComment(id: string) {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };
    return this.http.delete(`${url}/${id}` ,requestOptions);
  }

  editComment(id: string, dto: CommentDto) {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };
    return this.http.put(`${url}/${id}`, dto,requestOptions);
  }
}
