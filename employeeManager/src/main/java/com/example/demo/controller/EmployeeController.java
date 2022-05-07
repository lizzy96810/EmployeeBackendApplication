package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeRepo employeeRepository;
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		employeeRepository.save(employee);
		return ResponseEntity.ok(employee);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getEmployee(){
		List<Employee> employeeList = employeeRepository.findAll();
		return ResponseEntity.ok(employeeList);
	}
	@GetMapping("/find/{id}")
	public Optional<Employee> getEmployeById(@PathVariable ("id") Long id) {
		Optional<Employee> employee= employeeRepository.findById(id);
		return employee;
	}
	
	@PutMapping("/update")
	public ResponseEntity <Employee> updateEmployee( @RequestBody Employee employee){
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable("id")Long id) {
		Map<String,Boolean> deleteStatus = new HashMap<String,Boolean>();
		employeeRepository.deleteById(id);
		deleteStatus.put("deleted",true);
		return ResponseEntity.ok(deleteStatus);
	}
	
}
