--전체 조회
SELECT EMPNO, ENAME, JOB, MGR
 FROM EMP;

--상세 조회
SELECT EMPNO, ENAME, JOB, MGR, 
		TO_CHAR(HIREDATE,'YYYY-MM-DD') HIREDATE, SAL, 
		NVL(COMM,0) COMM 
 FROM EMP
 WHERE EMPNO='7900';

--입력
INSERT INTO EMP(EMPNO,ENAME,JOB,HIREDATE,SAL,DEPTNO) 
VALUES ((SELECT NVL(MAX(EMPNO),0)+1 FROM EMP),'TEST','TEST',
		SYSDATE, '1000',10);
	
SELECT * FROM EMP WHERE EMPNO='9991';

--수정
UPDATE EMP SET JOB='IT' WHERE EMPNO='9991';
	
--삭제
DELETE FROM EMP e 
 WHERE EMPNO='9991';