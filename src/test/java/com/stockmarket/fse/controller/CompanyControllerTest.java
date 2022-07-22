package com.stockmarket.fse.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.stockmarket.fse.exceptions.IllegalTurnoverException;
import com.stockmarket.fse.model.Company;
import com.stockmarket.fse.service.Service;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CompanyControllerTest {
	
	@Mock
	Service service;

	@InjectMocks
	CompanyController controller;
	
	
	@Test
	void addCompany() throws IllegalTurnoverException {

		Company company = new Company(1,"Company1","Sundareswar Das",new BigInteger("120000000"),"https://www.56789.com", "BSE");

		service.addCompany(company);
		verify(service, times(1)).addCompany(company);

	}
	
	
	@Test
	void getAll() {
		
		List<Company> companyList = new ArrayList<Company>(Arrays.asList(
		new Company(1,"Company1","Sundareswar Das",new BigInteger("120000000"),"https://www.56789.com", "BSE"),
		new Company(2,"Company2","Sundareswar Das",new BigInteger("120000000"),"https://www.12345.com", "BSE")
		));
		
		Mockito.lenient().when(service.getAll()).thenReturn(companyList);
		
		List<Company> cList = service.getAll();
		
		assertEquals(2, cList.size());
		verify(service, times(1)).getAll();
	}
	
	@Test
	void getCompany() {

		Company company = new Company(1,"Company1","Sundareswar Das",new BigInteger("120000000"),"https://www.56789.com", "BSE");

		Mockito.lenient().when(service.getCompany(1)).thenReturn(company);
		assertEquals("Sundareswar Das", company.getCeo());
	}

	@Test
	void deleteCompany() {		
		service.deleteCompany(1);
		verify(service, times(1)).deleteCompany(1);
	}
}
