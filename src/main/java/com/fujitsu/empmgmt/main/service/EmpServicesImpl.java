package com.fujitsu.empmgmt.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fujitsu.empmgmt.main.entity.Employee;
import com.fujitsu.empmgmt.main.repository.EmpRepository;

@Service
public class EmpServicesImpl implements EmpServices {
	
	@Autowired
	private EmpRepository emprepo;
	
	@Override
	public List<Employee> allEmp() {
		return emprepo.findAll();
	}
	
	@Override
	public void saveEmp(Employee e) {
		emprepo.save(e);
	}
	
	@Override
	public Employee empDetailsById(int id) {
		Optional<Employee> e = emprepo.findById(id);
		if(e.isPresent()) {
			return e.get();
		}
		return null;
	}
	
	@Override
	public void deleteEmpById(int id) {
		emprepo.deleteById(id);
	}
	
	@Override
	public Page<Employee> findEmpPageNo(int currentPage, int size){
		Pageable p = PageRequest.of(currentPage, size);
		return emprepo.findAll(p);
	}

}