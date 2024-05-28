package com.min.edu.dao;

import java.util.List;

import com.min.edu.dto.Emp_DTO;

//TODO 002 EMP CRUD의 기능을 정의하는 인터페이스
public interface IEmpDao {
	
	/**
	 * 사원 정보를 모두 조회하는 getAllEmp
	 * @return 모든 사원 조회 java.util.List<Emp_DTO>
	 */
	
	public List<Emp_DTO> getAllEmp();
	
	/**
	 * 사원 번호를 통한 사원의 상세 정보 getOneEmp
	 * @param 사원번호 int
	 * @return 사원상세정보 EMPNO,ENAME,JOB,HIREDATE,SAL,COMM
	 */
	public Emp_DTO getOneEmp(int empno);

	/**
	 * 사원정보 추가 setEmp
	 * @param 사원정보 ENAME, JOB, SAL
	 * @return 성공여부 실패 0, 성공 1
	 */
	public int setEmp(Emp_DTO dto);

	/**
	 * 사원정보 중 업무(job) 변경
	 * @param job 변경할 업무
	 * @param empno 변경할 사원 번호
	 * @return 성공여부 실패 0, 성공 1
	 */
	public int modifyEmp(String job, int empno);

	/**
	 * 회원정보 row를 삭제
	 * @param 삭제할 사원 번호
	 * @return 성공여부 실패 0, 성공 1
	 */
	public int delEmp(int empno);

}
