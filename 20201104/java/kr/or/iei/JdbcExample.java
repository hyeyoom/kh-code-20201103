package kr.or.iei;

import java.time.LocalDateTime;

public class JdbcExample {

    private long id;
    private String name;
    private LocalDateTime time;
    private String email;

    public JdbcExample(long id, String name, LocalDateTime time, String email) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.email = email;
    }

    @Override
    public String toString() {
        return "JdbcExample{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", email='" + email + '\'' +
                '}';
    }
}
