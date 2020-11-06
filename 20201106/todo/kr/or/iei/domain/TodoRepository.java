package kr.or.iei.domain;

import java.util.List;

public interface TodoRepository {
    void save(Todo todo);

    List<Todo> findAll();

    void remove(long id);
}
