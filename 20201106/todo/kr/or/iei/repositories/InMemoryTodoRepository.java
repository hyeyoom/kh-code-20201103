package kr.or.iei.repositories;

import kr.or.iei.domain.Todo;
import kr.or.iei.domain.TodoRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTodoRepository implements TodoRepository {

    private final List<Todo> list = new ArrayList<>();

    @Override
    public void save(Todo todo) {
        list.add(todo);
    }

    @Override
    public List<Todo> findAll() {
        return list;
    }

    @Override
    public void remove(long id) {
        Todo target = null;
        for (Todo todo : list) {
            if (todo.getId() == id) {
                target = todo;
                break;
            }
        }
        list.remove(target);
    }
}
