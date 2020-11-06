package kr.or.iei.service;

import kr.or.iei.domain.Todo;
import kr.or.iei.domain.TodoRepository;
import kr.or.iei.repositories.InMemoryTodoRepository;

import java.util.List;

public class TodoService {

    private TodoRepository repository = new InMemoryTodoRepository();

    public void addTodo(Todo todo) {
        repository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public void removeTodo(long id) {
        repository.remove(id);
    }
}
