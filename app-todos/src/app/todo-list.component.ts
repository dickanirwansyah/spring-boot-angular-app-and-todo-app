import { Component, OnInit } from '@angular/core';
import { Todo } from './todo';
import { NgForm } from '@angular/forms';
import { TodoService } from './todo.service';

@Component({
  selector: 'todo-list',
  templateUrl: './todo-list.component.html'
})
export class TodoListComponent implements OnInit{

    todos: Todo[];
    newTodo: Todo = new Todo();
    editing: boolean = false;
    editingTodo: Todo = new Todo();

    constructor(private todoService: TodoService){}

    ngOnInit():void {
      this.getTodos();
    }

    createTodo(todoForm: NgForm): void{
      this.todoService.createTodo(this.newTodo)
      .subscribe(createTodo => {
        todoForm.reset();
        this.newTodo = new Todo();
        this.todos.unshift(createTodo)
      });
    }

    updateTodo(todoData: Todo): void{
      console.log(todoData);
      this.todoService.updateTodo(todoData)
      .subscribe(updateTodo => {
        let existsTodo = this.todos.find(todo => todo.id === updateTodo.id);
        Object.assign(existsTodo, updateTodo)
        this.clearEditing()
      })
    }

    toggleComplete(todoData: Todo): void {
      todoData.completed =! todoData.completed;
      this.todoService.updateTodo(todoData)
      .subscribe(updateTodo => {
        let existsTodo = this.todos.find(todo => todo.id === updateTodo.id);
        Object.assign(existsTodo, updateTodo)
      });
    }
    
    getTodos(): void{
      this.todoService.getTodos()
      .subscribe(todos => this.todos = todos);
    }

    deleteTodo(id: string): void{
      this.todoService.deleteTodo(id)
      .subscribe(todos => this.todos.filter(todo => todo.id != id));
    }

    editTodo(todoData: Todo): void{
      this.editing = true;
      Object.assign(this.editingTodo, todoData);
    }

    clearEditing(): void{
        this.editingTodo = new Todo();
        this.editing = false;
    }

}
