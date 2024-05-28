package com.min.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.min.edu.comm.DataBase;
import com.min.edu.dto.Emp_DTO;

//TODO 013 IEmpTransactionDao를 구현하는 클래스
public class EmpTransactionDaoImpl extends DataBase implements IEmpTransactionDao {

	// TODO 014 다중삭제 (Batch): Oracle 21c는 Batch의 결과로, 각 row의 성공 시 1을 반환
	// {1,2,3}-> 성공->[1,1,1]

	@Override
	public int multiDeleteEmp(int[] empnos) {
		int n = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = " DELETE FROM EMP" + " WHERE EMPNO= ?";

		try {
			conn = getConnection();
			System.out.println("2단계");

			conn.setAutoCommit(false); // 연결된 커넥션에 Transaction 설정
			System.out.println("Transaction 시작");

			stmt = conn.prepareStatement(sql);
			System.out.println("3단계 쿼리 준비 중");

			for (int i = 0; i < empnos.length; i++) {
				stmt.setInt(1, empnos[i]);
				stmt.addBatch();
			}
			System.out.println("3단계 쿼리 준비완료");

			int[] BatchResult = stmt.executeBatch();
			// 하나의 배열로 만들어서 각각의 결과를 하나씩 반환
			System.out.println("4단계 쿼리 실행");

			conn.commit(); // Transaction 결과를 db에 저장하기 위함
			System.out.println("Transaction Commit 실행");
			System.out.println("실행결과 \n" + Arrays.toString(BatchResult));
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return n;
	}

	// TODO 015 Transaction 입력과 수정 묶음으로 처리
	@Override
	public int transactionEmp(Emp_DTO dto) {
		int n = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sqlInsert = " INSERT INTO EMP (EMPNO, ENAME, JOB," + " HIREDATE, SAL, DEPTNO) "
				+ " VALUES((SELECT NVL(MAX(EMPNO),0)+1 FROM EMP), ?,?, "
				+ " SYSDATE, ?, 10)";
		// 성공하는 insert

		String sqlUpdate = "UPDATE EMP SET JOB='IT'";
		//String sqlUpdate = "UPDATE EMP SET JOB='IT';";
		//실패하는 update- transaction에 걸리는지 확인

		try {
			conn = getConnection();
			System.out.println("2단계");

			conn.setAutoCommit(false);
			System.out.println("Transaction 시작");

			stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1, dto.getEname());
			stmt.setString(2, dto.getJob());
			stmt.setInt(3, dto.getSal());
			System.out.println("insert문 쿼리 준비");
			
			n+= stmt.executeUpdate();
			System.out.println("insert문 쿼리 실행");
			
			stmt= conn.prepareStatement(sqlUpdate);
			System.out.println("update문 쿼리 준비");
			
			n+=stmt.executeUpdate();
			System.out.println("update문 쿼리 실행");
			
			conn.commit();
			System.out.println("Transaction Commit 실행");

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			closed(rs,stmt,conn);
		}

		return n;
	}

}
