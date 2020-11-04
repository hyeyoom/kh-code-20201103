package kr.or.iei;

import java.sql.*;

public class Application9 {

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "oracle";

    public static void main(String[] args) throws ClassNotFoundException {
        // 1. 클래스 로드
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // 2. DB 접속
        try (final Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            final String sql = "UPDATE emp SET comm = 0 WHERE comm is null";
            final PreparedStatement pstmt = conn.prepareStatement(sql);
            final int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
