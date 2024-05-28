package com.min.edu.service;

import java.util.List;

import com.min.edu.dao.EmpDaoImpl;
import com.min.edu.dao.IEmpDao;
import com.min.edu.dto.Emp_DTO;
import com.util.edu.EducationUtils;

//기능 
//Dao Dto는 여러 번 만들어지지만 service는 한번만 만들어져서 필요할 때마다 실행(Singleton)
public final class EmpService {

	private static EmpService factory;
	private IEmpDao dao; // 멤버필드

	private EmpService() { // 생성자
		dao = new EmpDaoImpl();
	}
	public static EmpService getInstance() {
		if(factory==null) {
			factory=new EmpService();
		}
		return factory;
	}

	// TODO 010 DAO를 조합하여 기능을 만들어보자
	public Emp_DTO empModify() {
		Emp_DTO outDto = null;

		// 입력
		System.out.println("수정 사원번호를 입력해주세요");
		int empno = EducationUtils.inputNum();

		System.out.println("수정 업무를 입력해주세요");
		String job = EducationUtils.inputString();

		// 프로세스
		int n = dao.modifyEmp(job, empno);
		// 성공한 로우의 개수 반환

		if (n == 1) {
			System.out.println("성공적으로 수정되었습니다.\n 수정결과\n");
			outDto = dao.getOneEmp(empno);
		}

		return outDto;
	}

	// TODO 011 회원을 삭제하는 기능
	/**
	 * 회원을 삭제하는 기능
	 * 
	 * @return 삭제 성공 true/ 삭제 실패 false
	 */
	public boolean deleteEmp() {
		boolean isc = false;
		List<Emp_DTO> empLists = dao.getAllEmp();
		int idx = 1;

		for (Emp_DTO emp_DTO : empLists) {
			System.out.printf("%d: %d __ %s\n", idx++, emp_DTO.getEmpno(), emp_DTO.getEname());
		}

		System.out.println("사원 번호를 선택해주세요");
		int choiceEmpno = EducationUtils.inputNum();
		int delEmpno = empLists.get(choiceEmpno - 1).getEmpno();

		int n = dao.delEmp(delEmpno);

		return (n == 1) ? true : false;
	}
}
