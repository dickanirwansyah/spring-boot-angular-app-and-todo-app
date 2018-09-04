package com.spring.app.demoangular.repository.todos;

import com.spring.app.demoangular.entity.todos.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>{
}
