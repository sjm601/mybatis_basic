package com.ezen.mybatis;


import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ezen.mybatis.domain.employee.dto.Employee;
import com.ezen.mybatis.domain.employee.mapper.EmployeeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeMapperTest {
	SqlSession sqlSession;	
	
	@BeforeEach
	public void setUp() {
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@Test
	@Disabled
	public void findAllTest(){
		System.out.println("==================== 전체사원 조회 ========================");
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		log.debug("{}",mapper);
		List<Employee> list = mapper.findAll();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	@Test
	@Disabled
	public void findByIdTest(){
		System.out.println("==================== 사원번호로 사원조회 ========================");
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee employee = mapper.findById(150);
		System.out.println(employee);
	}
	
	@Test
	@Disabled
	public void findByLastNameTest(){
		log.debug("==================== 이름(성)으로 사원조회 ========================");
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		List<Employee> findEmployees = mapper.findByLastName("E	");
		findEmployees.forEach(employee->{
			log.debug("검색된 사원 : {}",employee);
		});
	}
	
	@Test
	@DisplayName("사원 등록")
	public void CreateTest(){
		Employee emp = new Employee();
		emp.setFirstName("Martin");
		emp.setLastName("Odegaard");
		emp.setEmail("Odegaard@gmail.com");
		emp.setPhoneNumber("010.8726.4490");
		emp.setHireDate("2023-08-08");
		emp.setJobId("IT_PROG");
		emp.setSalary(150000);
		emp.setManagerId(150);
		emp.setDepartmentId(60);
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		mapper.create(emp);
		sqlSession.commit();
		log.debug("사원등록 완료 : {}",emp);
	
	}
	
	@AfterEach
	public void destory() {
		sqlSession.close();
	}
}







