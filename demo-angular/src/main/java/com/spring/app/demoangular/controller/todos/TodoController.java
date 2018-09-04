package com.spring.app.demoangular.controller.todos;

import com.spring.app.demoangular.entity.todos.Todo;
import com.spring.app.demoangular.repository.todos.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/todo")
@CrossOrigin(origins = {"*"})
public class TodoController {

    @Autowired private TodoRepository todoRepository;

    @GetMapping
    public List<Todo> getTodos(){
        Sort shortAtByCreatedAt = new Sort(Sort.Direction.DESC, "createdAt");
        return todoRepository.findAll(shortAtByCreatedAt);
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    @GetMapping(value = "/{todoID}")
    public ResponseEntity<Todo> getTodoId(@PathVariable("todoID") Long todoID){
        return todoRepository.findById(todoID)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{todoID}")
    public ResponseEntity<Todo> editTodoById(@PathVariable("todoID") Long todoID,
                                             @RequestBody Todo todo){
        return todoRepository.findById(todoID)
                .map(existsByTodo -> {
                    existsByTodo.setTitle(todo.getTitle());
                    existsByTodo.setCompleted(todo.isCompleted());
                    Todo updateTodo = todoRepository.save(existsByTodo);
                    return ResponseEntity.ok().body(updateTodo);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{todoID}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("todoID") Long todoID){
        return todoRepository.findById(todoID)
                .map(existsByTodo -> {
                    todoRepository.delete(existsByTodo);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
