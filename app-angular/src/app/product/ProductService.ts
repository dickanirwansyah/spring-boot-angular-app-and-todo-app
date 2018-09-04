import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService{

  private baseUrl = "http://localhost:8888/start-app/api/product";

  constructor(private _httpClient: HttpClient){}

  /** get list product **/
  getProductList():Observable<any>{
    return this._httpClient.get(`${this.baseUrl}`);
  }

  /** get byid product **/
  getProductById(productId: number):Observable<any>{
    return this._httpClient.get(`${this.baseUrl}/${productId}`);
  }

  /** save product **/
  getProductSave(product: Object):Observable<any>{
    return this._httpClient.post(`${this.baseUrl}/create/${}`, product);
  }
}
