package com.min.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.min.edu.comm.DataBase;
import com.min.edu.dto.Emp_DTO;

//TODO 005 공통모듈과 기능 정의를 구현하는 DAO 클래스
public class EmpDaoImpl extends DataBase implements IEmpDao {

	// TODO 006 getAllEmp 모든 사원 조회
	@Override
	public List<Emp_DTO> getAllEmp() {
		List<Emp_DTO> empLists = new ArrayList<Emp_DTO>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = " SELECT EMPNO, ENAME, JOB, MGR" + " FROM EMP "; // 쿼리문 앞뒤로 한칸씩 띄어주기

		try {
			conn = getConnection();
			System.out.println("2단계 드라이버 로딩 성공");

			stmt = conn.prepareStatement(sql);
			System.out.println("3단계 쿼리 준비 성공");

			rs = stmt.executeQuery();
			System.out.println("4단계 쿼리 실행");

			while (rs.next()) {
				Emp_DTO temp = new Emp_DTO();

				// EMPNO,ENAME,JOB,MGR 담아야 됨
				temp.setEmpno(rs.getInt(1));
				temp.setEname(rs.getString(2));
				temp.setJob(rs.getString(3));
				temp.setMgr(rs.getInt(4));

				empLists.add(temp);
			}
			System.out.println("5단계 결과값 받기");

		} catch (SQLException e) {
			System.out.println("getAllEmp 실패");
			e.printStackTrace();
		} finally {
			closed(rs, stmt, conn);
		}

		return empLists;
	}// getAllEmp

	// TODO 007 getOneEmp 사원번호 입력을 통한 사원 상세조회
	@Override
	public Emp_DTO getOneEmp(int empno) {
		Emp_DTO dto = new Emp_DTO();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = " SELECT EMPNO,ENAME, JOB, MGR, TO_CHAR(HIREDATE,'YYYY-MM-DD') HIREDATE, SAL, NVL(COMM,0) COMM"
				+ " FROM EMP " + " WHERE EMPNO=?"; // JDBC에서 PreparedStatement의 값을 바인딩(binding)하는 부분은 ?로 작성
		// 쿼리문 앞 뒤 띄어주기

		try {
			conn = getConnection();
			System.out.println("2단계 커넥션 성공");

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empno);
			System.out.println("3단계 쿼리 준비 성공");

			rs = stmt.executeQuery();
			System.out.println("4단계 쿼리 실행 성공");

			while (rs.next()) {
				// SQL 문의 값을 가져올 때는 컬럼의 index(1부터), 컬럼명으로 작성
				dto.setEmpno(rs.getInt("EMPNO"));
				dto.setEname(rs.getString("ENAME"));
				dto.setJob(rs.getString("JOB"));
				dto.setHiredate(rs.getString("HIREDATE"));
				dto.setSal(rs.getInt("SAL"));
				dto.setComm(rs.getInt("COMM"));
			}
			System.out.println("5단계 결과 값 받기 성공");

		} catch (SQLException e) {
			System.out.println("getOneEmp 실패");
			e.printStackTrace();
		} finally {
			closed(rs, stmt, conn);
		}
		return dto;
	}

	// TODO 사원 정보 입력
	@Override
	public int setEmp(Emp_DTO dto) {
		int rowNum = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// ENAME, JOB, SAL 필요
		String sql = " INSERT INTO EMP(EMPNO,ENAME,JOB,HIREDATE,SAL,DEPTNO) "
				+ " VALUES((SELECT NVL(MAX(EMPNO),0)+1 FROM EMP),?,?,SYSDATE,?,10) ";

		try {
			conn = getConnection();
			System.out.println("2단계");

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getEname());
			stmt.setString(2, dto.getJob());
			stmt.setInt(3, dto.getSal());

			System.out.println("3단계");

			rowNum = stmt.executeUpdate();
			System.out.println("4단계");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closed(rs, stmt, conn);
		}

		return rowNum;

	}

	//TODO 010 modifyEmp 사원번호에 해당하는 업무(JOB)을 변경해본다
	@Override
	public int modifyEmp(String job, int empno) {
		int rowNum=0;
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs= null;
		
		String sql=" UPDATE EMP SET JOB=? "
				+ " WHERE EMPNO= ? ";
		//자동으로 '' 이 붙어서 들어가므로 '?' 안해도 됨
		
		try {
			conn = getConnection();
			System.out.println("2단계");

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, job);
			stmt.setInt(2, empno);

			System.out.println("3단계");

			rowNum = stmt.executeUpdate();
			System.out.println("4단계");

		} catch (SQLException e) {
			System.out.println("modifyEmp 실패");
			e.printStackTrace();
		} finally {
			closed(rs, stmt, conn);
		}

		
		return rowNum;

	}

	//
	@Override
	public int delEmp(int empno) {
		int rowNum=0;
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs= null;
		
		String sql=" DELETE FROM EMP"
				+ " WHERE EMPNO=? ";
		
		try {
			conn = getConnection();
			System.out.println("2단계");

			stmt = conn.prepareStatement(sql);		
			stmt.setInt(1, empno);
			System.out.println("3단계");

			rowNum = stmt.executeUpdate();
			System.out.println("4단계");

		} catch (SQLException e) {
			System.out.println("delete 실패");
			e.printStackTrace();
		} finally {
			closed(rs, stmt, conn);
		}

		
		return rowNum;

	}

}
