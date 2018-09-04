import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService{

    /** url java api **/
    private baseUrl = "http://localhost:8888/start-app/api/category";

    constructor(private _httpClient: HttpClient){}

    /** find by id **/
    getCategoryId(id: number): Observable<any>{
        return this._httpClient.get(`${this.baseUrl}/${id}`);
    }

    /** find all **/
    getCategoryList(): Observable<any>{
      return this._httpClient.get(`${this.baseUrl}`);
    }

    /** update category
    getUpdateCategory(,category: Object, categoryId: number):Observable<Object>{
      return this._httpClient.put(`${this.baseUrl}/${categoryId}`, category);
    } **/

    /** create new **/
    getNewCategory(category: Object):Observable<Object>{
      return this._httpClient.post(`${this.baseUrl}`, category);
    }
}
