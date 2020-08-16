package com.rdao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testClass.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
