package com.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdao.CompanyRepository;
import com.testClass.Company;
import com.testClass.Employee;
import com.util.Response;

@RestController
@RequestMapping("/rest")
@Transactional
public class RestApiControl {

	@Autowired
	private CompanyRepository companyDao;

//	@Autowired
//	private EmployeeRepository employeeDao;

	@GetMapping("/companies")
	public List<Company> showAllCompany() {
		List<Company> allList = companyDao.findAll();
		return allList;

	}

	@GetMapping("/companies/{cid}/employees")
	public List<Employee> showAllEmployee(@PathVariable int cid) {
		Company company = companyDao.findById(cid).get();
		List<Employee> allList = company.getEmpList();
		return allList;

	}

	@GetMapping("/company/{cid}")
	public Company showCompany(@PathVariable int cid) {
		Company company = companyDao.findById(cid).get();
		return company;
	}

	@GetMapping("/company/{cid}/employee/{eid}")
	public Employee showEmployee(@PathVariable int cid, @PathVariable int eid) {
		Company company = companyDao.findById(cid).get();
		List<Employee> employee = company.getEmpList();
		for (Employee e : employee) {
			if (eid == e.getEid()) {
				return e;
			}
		}
		return null;
	}

	@PostMapping("/company")
	public Response addCompany(@RequestBody Company company) {
		companyDao.save(company);
		Response response = new Response();
		response.setCode(1);
		response.setMessage("Comapny Successfully Created");
		return response;
	}

	@PostMapping("/company/{cid}/employee")
	public Response addEmployee(@RequestBody Employee employee, @PathVariable int cid) {
		// employee.setCid(cid);

		Company company = companyDao.getOne(cid);
		List<Employee> elist = company.getEmpList();
		elist.add(employee);
		// employeeDao.save(employee);
		Response response = new Response();
		response.setCode(11);
		response.setMessage("Employee Successfully Created");
		return response;
	}

	@DeleteMapping("/company/{cid}")
	public Response deleteCompany(@PathVariable int cid) {
		companyDao.deleteById(cid);
		Response response = new Response();
		response.setCode(2);

		response.setMessage("Company Deleted");
		return response;
	}

	@DeleteMapping("/company/{cid}/employee/{eid}")
	public Response deleteEmployee(@PathVariable int cid, @PathVariable int eid) {
		Company compList = companyDao.findById(cid).get();
		System.out.println(compList);
		List<Employee> empList = compList.getEmpList();
		for (Employee e : empList) {
			System.out.println(e);
			if (e.getEid() == eid) {
				empList.remove(e);
				break;
				// System.out.println(e);
			}
		}
		// System.out.println(empList);
		Response response = new Response();
		response.setCode(22);

		response.setMessage("Employee Deleted");
		return response;
	}

	@PutMapping("/company/{cid}")
	public Response updateCompany(@PathVariable int cid, @RequestBody Company incomingData) {
		Company company = companyDao.findById(cid).get();
		if (incomingData.getName().length() != 0) {
			company.setName(incomingData.getName());
		}
		if (incomingData.getAddress().length() != 0) {
			company.setAddress(incomingData.getAddress());
		}
		Response response = new Response();
		response.setCode(13);
		response.setMessage("Company Successfully Updated");
		return response;
	}

	@PutMapping("/company/{cid}/employee/{eid}")
	public Response updateEmployee(@PathVariable int cid, @PathVariable int eid, @RequestBody Employee incomingData) {
		Company company = companyDao.findById(cid).get();
		List<Employee> empList = company.getEmpList();
		for (Employee e : empList) {
			if (e.getEid() == eid) {
				if (incomingData.getName().length() != 0) {
					e.setName(incomingData.getName());
				}
				if (incomingData.getSalary() != 0) {

					e.setSalary(incomingData.getSalary());
				}
			}
		}

		Response response = new Response();
		response.setCode(23);
		response.setMessage("Employee Successfully Updated");
		return response;
	}
}
