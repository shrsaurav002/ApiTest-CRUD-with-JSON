package com.testClass;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company_tbl")
public class Company {

	@Id
	@GeneratedValue
	@Column(name = "cid")
	private int cid;
	@Column
	private String name;
	@Column
	private String address;

	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "cid")
	private List<Employee> empList;

	@Override
	public String toString() {
		return "Company [cid=" + cid + ", name=" + name + ", address=" + address + ", empList=" + empList + "]";
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company( String name, String address, List<Employee> empList) {
		super();
		this.name = name;
		this.address = address;
		this.empList = empList;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

}
