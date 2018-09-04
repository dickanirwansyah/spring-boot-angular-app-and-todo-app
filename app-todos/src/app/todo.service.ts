import { Injectable } from '@angular/core';
import { Todo } from './todo';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TodoService{

  private baseUri = "http://localhost:8888";

  constructor(private _http: HttpClient){}

  getTodos(): Observable<any>{
      return this._http.get(this.baseUri+'/start-app/api/todo');
  }

  createTodo(todoData: Todo): Observable<any>{
    return this._http.post(`${this.baseUri}/start-app/api/todo`, todoData);
  }

  updateTodo(todoData: Todo): Observable<any> {
    return this._http.put(this.baseUri + '/start-app/api/todo/'+todoData.id, todoData);
  }

  deleteTodo(id: string): Observable<any>{
    return this._http.delete(this.baseUri + '/start-app/api/todo/'+id);
  }

}
