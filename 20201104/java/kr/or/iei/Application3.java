package kr.or.iei;

import java.sql.*;
import java.time.LocalDateTime;

public class Application3 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (final Connection conn =
                     DriverManager.getConnection(
                             "jdbc:oracle:thin:@localhost:1521:XE",
                             "system", "oracle"
                     );
        ) {
            final String sql = "SELECT * FROM jdbc_example WHERE id = 4";
            final PreparedStatement pstmt = conn.prepareStatement(sql);
            final ResultSet rs = pstmt.executeQuery();
            rs.next();
            final int id = rs.getInt("id");
            final String name = rs.getString("name");
            final LocalDateTime localDateTime = rs.getTimestamp("time").toLocalDateTime();
            final String email = rs.getString("email");
            System.out.println(id);
            System.out.println(name);
            System.out.println(localDateTime);
            System.out.println(email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
