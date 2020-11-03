package kr.or.iei;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        // 1. jvm에 클래스 로드 (Oracle JDBC Driver)
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // 2. 드라이버 매니저로부터 커넥션 얻어옴
        try (final Connection conn =
                     DriverManager.getConnection(
                             "jdbc:oracle:thin:@localhost:1521:XE",
                             "system", "oracle"
                     );
             // 3. 데이터베이스 작업
             final PreparedStatement pstmt =
                     conn.prepareStatement("SELECT 1 FROM dual");
        ) {

            final ResultSet rs =    // query 결과를 담는 객체
                    pstmt.executeQuery();    // query 실행
            rs.next();
            int value = rs.getInt(1);
            System.out.println(value);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
