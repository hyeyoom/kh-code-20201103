package kr.or.iei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Application7 {
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "oracle";

    public static void main(String[] args) throws ClassNotFoundException {
        // 1. 클래스 로드
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // 2. 데이터베이스에 접속
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            final boolean 이전_커넥션의_autocommit_설정상태 = conn.getAutoCommit();
            conn.setAutoCommit(false);

            String sql1 = "UPDATE bank_account SET money = 0 WHERE username = 'A'";
            String sql2 = "UPDATE bank_account SET money = 1000 WHERE username = 'B'";

            // A의 계좌를 0으로
            final PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            final int affectedRows1 = pstmt1.executeUpdate();
            System.out.println("1: " + affectedRows1);

            // B의 계좌를 1000으로
            final PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            final int affectedRows2 = pstmt2.executeUpdate();
            System.out.println("2: " + affectedRows2);

            conn.commit();
            conn.setAutoCommit(이전_커넥션의_autocommit_설정상태);
        } catch (SQLException throwables) {
            if (conn != null) {
                try {
                    System.out.println("제발 롤백");
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        } finally {
            close(conn);
        }
    }

    private static void 에러발생함() throws SQLException {
        if (System.currentTimeMillis() > 0) {
            throw new SQLException("롤백 테스트");
        }
    }

    private static void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
