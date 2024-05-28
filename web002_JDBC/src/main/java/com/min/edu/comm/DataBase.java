package com.min.edu.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//TODO 003 DataBase 드라이버로딩, 접속, 닫기의 공통 기능을 모듈화한 공통 메소드 집합 
//jdbc 1,2,6 단계

public /*abstract*/ class DataBase {
	/**
	 * 부모의 생성자를 통해서 구현된 기능 클래스에서 자동으로 드라이버가 로딩되도록 함
	 */
	public DataBase() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1단계 드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("1단계 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}//DataBase
	
	
	/**
	 * RDBMS를 연결하여 접속 정보 객체인 java.sql.Connection을 반환하는 메소드
	 * @return java.sql.Connection()
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException {
		Connection conn=null;
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="EDU";
		String password="EDU";
		conn=DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	/**
	 * 사용되어진 java.sql.* 객체를 닫아주는 메소드
	 * @param rs java.sql.ResultSet
	 * @param stmt java.sql.Statement
	 * @param conn java.sql.Connection
	 */
	public void closed(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
			System.out.println("6단계 닫기 성공");
		} catch (SQLException e) {
			System.out.println("6단계 닫기 실패");			
			e.printStackTrace();
		}
	}
}
