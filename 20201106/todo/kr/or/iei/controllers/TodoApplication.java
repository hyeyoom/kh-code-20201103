package kr.or.iei.controllers;

import kr.or.iei.domain.Todo;
import kr.or.iei.service.TodoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

// 할일 한줄 추가
// 목록
// 삭제
public class TodoApplication {

    private final Scanner scanner = new Scanner(System.in);
    private final TodoService todoService = new TodoService();

    public void run() {
        while (true) {
            renderMenu();
            final int selectedAction = chooseMenu();
            act(selectedAction);
        }
    }

    private void act(int selectedAction) {
        switch (selectedAction) {
            case 1:
                renderAddingTodo();
                break;
            case 2:
                renderTodoList();
                break;
            case 3:
                renderRemoveMenu();
                break;
            default:
                System.out.println("망");
                break;
        }
    }

    private void renderRemoveMenu() {
        final long id = scanner.nextLong();
        todoService.removeTodo(id);
    }

    private void renderTodoList() {
        System.out.println("2. Todo List");
        final List<Todo> todoList = todoService.getAllTodos();
        todoList.forEach(System.out::println);
        System.out.println("-----------------------");
    }

    private void renderAddingTodo() {
        System.out.println("1. 할 일을 추가해주세요 ====");
        System.out.print("할 일 추가 > ");
        final String message = scanner.next();
        final Todo todo = new Todo(message, LocalDateTime.now());
        todoService.addTodo(todo);
    }

    private int chooseMenu() {
        System.out.print("선택 > ");
        return scanner.nextInt();
    }

    private void renderMenu() {
        System.out.println("1. 할 일 추가");
        System.out.println("2. 할 일 목록");
        System.out.println("3. 할 일 삭제");
    }

}
