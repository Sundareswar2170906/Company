package com.stockmarket.fse.service;

import java.util.List;

import com.stockmarket.fse.exceptions.IllegalTurnoverException;
import com.stockmarket.fse.model.Company;

public interface IService {

	public Company addCompany(Company company) throws IllegalTurnoverException;
	
	public List<Company> getAll();

	public Company getCompany(int companyCode);
	
	public void deleteCompany(int companyCode);
}
