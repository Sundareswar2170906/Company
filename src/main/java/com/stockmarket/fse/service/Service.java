package com.stockmarket.fse.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.stockmarket.fse.exceptions.IllegalTurnoverException;
import com.stockmarket.fse.model.Company;
import com.stockmarket.fse.repository.ICompanyRepository;

@org.springframework.stereotype.Service
public class Service implements IService {
	
	private String kafkaTopic= "company-logs";

	@Autowired
	private KafkaTemplate<String, String> template;
	
	@Autowired
	private ICompanyRepository repo;

	public Company addCompany(Company company) throws IllegalTurnoverException {
		//template.send(kafkaTopic, "Add New Company - Company Code : " + company.getCode());
		BigInteger minimumTurnover = new BigInteger("100000000");
		int compare = company.getTurnover().compareTo(minimumTurnover);
		System.out.println(compare);
		if(compare<1) {
			throw new IllegalTurnoverException();
		}
		
		
		return repo.save(company);
	}

	public List<Company> getAll() {
		//template.send(kafkaTopic, "Get All ");
		return repo.findAll();
	}

	public Company getCompany(int companyCode) {
		//template.send(kafkaTopic, "Get Company Details - Company Code : " + companyCode);
		return repo.findById(companyCode).get();
	}

	public void deleteCompany(int companyCode) {
		//template.send(kafkaTopic, "Delete Company - Company Code : " + companyCode);
		repo.deleteById(companyCode);
	}

}
