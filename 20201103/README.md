# JDBC 객체

- DriverManager: 드라이버 관리
- Connection: 특정 DB에 대한 커넥션(세션).
- DB 작업을 위한 객체
    1. Statement
    2. PreparedStatement
- ResultSet: 쿼리 결과를 받아서 저장하는 객체

# Statement, PreparedStatement

- executeQuery: 반환을 ResultSet. DQL (SELECT)
- executeUpdate: 반환은 int. 영향 받은 행 수. DML(insert, update, delete)

차이점

- prepared statement는 캐시

# ResultSet

- DQL 수행 결과 저장

- get타입(int columnIndex);
- get타입(String columnLabel);

# CRUD

Create(insert) Read(select) Update(update) Delete(delete)

## Create

## READ

BOF
1	chwon	20/11/03 10:52:44.000000000        <<<< cursor 	
2	abc	20/11/03 10:56:25.000000000	
3	abc	20/11/03 11:02:04.000000000	
4	abc	20/11/03 11:09:07.000000000	a@b.com
EOF

## 실행 단계

1. SQL 구문 분석
2. 컴파일
3. 실행

statement는 1~3단계 매번 실행
prepared statement는 1~3단계 캐시 이후에 생략

## 트랜잭션 관련 메소드

Connection 객체

- commit()
- rollback()
- setAutoCommit(boolean);   
- getAutoCommit();

## Result Set

BOF     <<< cursor
1	A	0
2	B	1000
EOF

## 자원 해제

Connection, Statement, PreparedStatement, ResultSet 등은 close()로 닫아 주어야 한다.  