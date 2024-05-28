package com.min.edu;

import com.min.edu.dao.EmpDaoImpl;
import com.min.edu.dao.IEmpDao;
import com.min.edu.dto.Emp_DTO;
import com.min.edu.service.EmpService;
import com.util.edu.EducationUtils;

public class EmpMain {

	public static void main(String[] args) {
		/*
		 * System.out.println("사원번호를 입력해주세요");
		 * 
		 * int empno = EducationUtils.inputNum(); 
		 * IEmpDao dao = new EmpDaoImpl();
		 * Emp_DTO oneEmp = dao.getOneEmp(empno); 
		 * System.out.println(oneEmp);
		 */

		
		/*
		 * Emp_DTO inDto=new Emp_DTO(
		 * EducationUtils.inputString(),
		 * EducationUtils.inputString(),
		 *  EducationUtils.inputNum());
		 */

		
	//	EmpService service = new EmpService();
		
		EmpService service=EmpService.getInstance();
		
		/*
		Emp_DTO returnDto = service.empModify();
		if (returnDto != null) {
			System.out.println(returnDto);
		} else {
			System.out.println("수정에 실패하셨습니다.");
		}
		*/
		
		boolean isc= service.deleteEmp();
		if (isc) {
			System.out.println("삭제되었습니다");
		} else {
			System.out.println("삭제에 실패하였습니다");
		}

	}

}
