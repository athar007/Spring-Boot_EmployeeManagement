package com.fujitsu.empmgmt.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fujitsu.empmgmt.main.entity.Employee;
import com.fujitsu.empmgmt.main.service.EmpServicesImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {
	
	@Autowired
	private EmpServicesImpl services;
	
	@GetMapping("/")
	public String home(Model m) {
		return findPageNo(0, m);
	}
	
	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e, HttpSession session) {
		//System.out.println(e);
		services.saveEmp(e);
		session.setAttribute("msg", "Emplyee Added Sucessfully!");
		return "redirect:/";
		//return "add_emp";
	}
	
	@GetMapping("/edit/{id}")
	public String editEmp(@PathVariable int id, Model mod) {
		Employee empp = services.empDetailsById(id);
		mod.addAttribute("empp", empp);
		return "edit";
		
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
		services.saveEmp(e);
		session.setAttribute("msg", "Employee updated sucessfully");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {
		services.deleteEmpById(id);
		session.setAttribute("msg", "Employee deleted sucessfully");
		return "redirect:/";
	}
	
	@GetMapping("/page/{pno}")
	public String findPageNo(@PathVariable int pno, Model m) {
		Page<Employee> emplist = services.findEmpPageNo(pno, 5);
		m.addAttribute("emp", emplist);
		m.addAttribute("currentPage", pno);
		m.addAttribute("totalPages", emplist.getTotalPages());
		m.addAttribute("totalItem", emplist.getTotalElements()); 
		return "index";
		
		
	}
	
	@GetMapping("/page/addemp")
	public String redirectToAddEmp() {
		return "add_emp";
	}

}
