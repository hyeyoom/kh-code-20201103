package kr.or.iei.domain;

import java.time.LocalDateTime;

public class Todo {

    private long id = System.currentTimeMillis();
    private String message;
    private LocalDateTime writtenDate;

    public Todo(String message, LocalDateTime writtenDate) {
        this.message = message;
        this.writtenDate = writtenDate;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", writtenDate=" + writtenDate +
                '}';
    }
}
