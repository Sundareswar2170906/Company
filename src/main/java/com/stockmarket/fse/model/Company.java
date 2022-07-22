package com.stockmarket.fse.model;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public int code;
	
	public String name;
	
	public String ceo;
	
	public BigInteger turnover;

	
	public String website;
	
	public String exchange;
	
}
