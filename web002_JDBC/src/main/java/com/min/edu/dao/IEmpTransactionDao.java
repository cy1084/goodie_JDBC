package com.min.edu.dao;

import com.min.edu.dto.Emp_DTO;

//TODO 012 Transaction/ Batch 기능을 정의
public interface IEmpTransactionDao {
	/**
	 * 여러 개의 사원번호를 입력받아 batch 를 통해 삭제
	 * @param array로 되어 있는 사원 번호
	 * @return ??
	 */
	public int multiDeleteEmp(int[] empnos);
	
	/**
	 * 입력(insert) + 수정(update) 시 예외가 발생하면 모든 수행을 rollback 처리
	 * @param 입력값 DTO
	 * @return ??
	 */
	public int transactionEmp(Emp_DTO dto);
	
}
