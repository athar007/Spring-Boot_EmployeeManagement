package com.fujitsu.empmgmt.main.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fujitsu.empmgmt.main.entity.Employee;


public interface EmpServices {
	
	List<Employee> allEmp();
	Employee empDetailsById(int id);
	void deleteEmpById(int id);
	void saveEmp(Employee e);
	Page<Employee> findEmpPageNo(int currentPage, int size);
	//void updateEmp();
}
