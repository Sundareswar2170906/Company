package com.stockmarket.fse.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.stockmarket.fse.model.Company;
import com.stockmarket.fse.repository.ICompanyRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ServiceTest {

	@InjectMocks
	Service service;

	@Mock
	ICompanyRepository dao;
	

	@Test
	void addCompany() {

		Company company = new Company(1,"Company1","Sundareswar Das",new BigInteger("120000000"),"https://www.56789.com", "BSE");

		dao.save(company);
		verify(dao, times(1)).save(company);

	}
	
	
	@Test
	void getAllCompaniesTest() {
		
		List<Company> companyList = new ArrayList<Company>(Arrays.asList(
		new Company(1,"Company1","Sundareswar Das",new BigInteger("120000000"),"https://www.56789.com", "BSE"),
		new Company(2,"Company2","Sundareswar Das",new BigInteger("120000000"),"https://www.12345.com", "BSE")
		));
		
		when(dao.findAll()).thenReturn(companyList);
		
		List<Company> cList = dao.findAll();
		
		assertEquals(2, cList.size());
		verify(dao, times(1)).findAll();
	}
	
	@Test
	void getCompany() {

		Optional<Company> company = Optional.of(new Company(1,"Company1","Sundareswar Das",new BigInteger("120000000"),"https://www.56789.com", "BSE"));

		Mockito.lenient().when(dao.findById(1)).thenReturn(company);
		assertEquals("Sundareswar Das", company.get().getCeo());
	}

	@Test
	void deleteCompany() {		
		dao.deleteById(1);
		verify(dao, times(1)).deleteById(1);
	}

}
