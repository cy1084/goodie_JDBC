package com.test.edu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.min.edu.comm.DataBase;
import com.min.edu.dao.EmpDaoImpl;
import com.min.edu.dao.IEmpDao;
import com.min.edu.dao.IEmpTransactionDao;
import com.min.edu.dao.EmpTransactionDaoImpl;
import com.min.edu.dto.Emp_DTO;
import com.util.edu.EducationUtils;

public class JDBC_TestJUnit {

//	@Test
	public void test() {
		String str = EducationUtils.inputString();
		assertNotEquals("", str);
	}

	/**
	 * TODO 004 DataBase 공통 모듈 JUnit 테스트
	 */
	// @Test
	public void database_Test() {
		DataBase db = new DataBase();
		Connection conn = null;

		try {
			conn = db.getConnection();
			System.out.println("2단계 드라이버 로딩 성공");
		} catch (SQLException e) {
			System.out.println("2단계 드라이버 로딩 실패");
			e.printStackTrace();
		}
		db.closed(null, null, conn);
		assertNotNull(conn);
		// null이 아니라면 true가 뜰 것임

	}// database_test

	// @Test
	public void getAllEmp_JUnitTest() {
		IEmpDao dao = new EmpDaoImpl();
		List<Emp_DTO> lists = dao.getAllEmp();

		System.out.println(lists);
		assertNotEquals(0, lists.size());
	}

	// @Test
	public void getOneEmp_JUnitTest() {
		IEmpDao dao = new EmpDaoImpl();
		Emp_DTO dto = dao.getOneEmp(7900);

		System.out.println("상세조회 결과: " + dto);
		assertNotNull(dto.getEname());

	} // getOneEmp test

	// @Test
	public void setEmp_JUnitTest() {
		IEmpDao dao = new EmpDaoImpl();
		Emp_DTO inDto = new Emp_DTO("Dev", "IT", 2000);
		int rowNum = dao.setEmp(inDto);
		assertEquals(1, rowNum);
	}

	// @Test
	public void modifyEmp_JUnitTest() {
		IEmpDao dao = new EmpDaoImpl();
		int rowNum = dao.modifyEmp("Software", 7935);

		assertEquals(1, rowNum);
	}

	// @Test
	public void batchDelete_JUnitTest() {
		int[] empnos = {};
		IEmpTransactionDao tDao = new EmpTransactionDaoImpl();
		tDao.multiDeleteEmp(empnos);

	}

	@Test
	public void transaction_JUnitTest() {
		Emp_DTO dto = new Emp_DTO("Happy", "DEV", 2000);
		IEmpTransactionDao tDao = new EmpTransactionDaoImpl();
		int n = tDao.transactionEmp(dto);
		System.out.println(n);
	}
}
