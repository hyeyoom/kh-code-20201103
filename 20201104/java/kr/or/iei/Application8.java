package kr.or.iei;

import java.sql.*;

public class Application8 {

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "oracle";

    public static void main(String[] args) throws ClassNotFoundException {
        // 1. 드라이버 클래스 로드
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // 2. 커넥션을 얻는다 - DB에 접속(세션)
        try (final Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // 3. 데이터베이스 작업
                // 1. executeQuery - ResultSet
            test1(conn);
                // 2. executeUpdate - int (영향 받은 행의 수)
            test2(conn);
                // 3. 트랜잭션 제어
            test3(conn);
            // 4. 연결 종료
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void test3(Connection conn) throws SQLException {
        conn.setAutoCommit(false);  // auto commit 설정. true면 켬.
        conn.commit();  // commit - 물리적인 저장소에 반영됨
        conn.rollback();    // rollback - 반영할 내용들이 취소됨
        conn.getAutoCommit();   // 현재 auto commit 설정 상태를 나타냄. true면 켜진 상태.
    }

    private static void test2(Connection conn) throws SQLException {
        // 1. 파라미터 세팅 - setXXXXX(파라미터번호, 값)
        // 2. executeUpdate의 결과는 영향 받은 행의 개수를 나타냄
        final String sql = "INSERT INTO bank_account VALUES (?, ?, ?)";
        final PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, 10);
        pstmt.setString(2, "chwon");
        pstmt.setLong(3, 10000);
        final int affectedRows = pstmt.executeUpdate();
        System.out.println(affectedRows);
    }

    private static void test1(Connection conn) throws SQLException {
        // 1. prepared statement
        // 2. executeQuery로 SELECT문 실행
        // 3. 그 결과는 ResultSet임
        // 4. cursor
        final String sql = "SELECT * FROM bank_account";
        final PreparedStatement pstmt = conn.prepareStatement(sql);
        final ResultSet resultSet = pstmt.executeQuery();

        while(resultSet.next()) {
            final int id = resultSet.getInt("id");
            final String username = resultSet.getString("username");
            final int money = resultSet.getInt("money");
            System.out.println(id);
            System.out.println(username);
            System.out.println(money);
            System.out.println("---------------------");
        }
    }
}
