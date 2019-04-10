import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategoriesResponse } from '../interfaces/categories-response.interface';
import { categoryDto } from '../model/dto/category.dto';

const url = `${environment.apiUrl}/categories`;
const accessToken = `${environment.masterKey}`;

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  getAll(): Observable<CategoriesResponse> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };
    return this.http.get<CategoriesResponse>(`${url}` ,requestOptions);
  }

  deleteCategory(id: string) {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };
    return this.http.delete(`${url}/${id}` ,requestOptions)
  }

  createCategory(dto: categoryDto){
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`,
        'Access-Control-Allow-Origin': ''
      })
    };

    return this.http.post(`${url}`, dto, requestOptions)
  }


}
