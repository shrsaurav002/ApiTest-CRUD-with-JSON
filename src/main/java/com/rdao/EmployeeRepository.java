package com.rdao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testClass.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	void deleteByEid(int eid);

}
