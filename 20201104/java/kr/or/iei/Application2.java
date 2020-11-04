package kr.or.iei;

import java.sql.*;

public class Application2 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (final Connection conn =
                     DriverManager.getConnection(
                             "jdbc:oracle:thin:@localhost:1521:XE",
                             "system", "oracle"
                     );
        ) {
            final String name = "abc";
            final String email = "a@b.com";
            final String sql = "INSERT INTO jdbc_example VALUES(seq_jdbc_example_pk.nextval, ?, DEFAULT, ?)";
            final PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            final int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
