package com.stockmarket.fse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.stockmarket.fse.exceptions.IllegalTurnoverException;
import com.stockmarket.fse.model.Company;
import com.stockmarket.fse.service.Service;

@RestController
public class CompanyController {
	
	@Value("${cloud.aws.end-point.uri}")
	private String sqsUrl;
	
	@Value("${cloud.aws.end-point.sns}")
	private String snsUrl;
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;

//	@Autowired
//	private AmazonSNSClient amazonSNSClient;
	
	@Autowired
	private Service service;
	
	//String TOPIC_ARN = "arn:aws:sns:ap-south-1:853447314930:newtopic";

	@PostMapping("/api/v1.0/market/company/register")
	public ResponseEntity<Company> addCompany(@RequestBody Company company) {

		Company c = null;
		try {
			c = service.addCompany(company);
		} catch (IllegalTurnoverException ex) {
			System.out.println("Exception in Turnover");
		}
		queueMessagingTemplate.send(sqsUrl,MessageBuilder.withPayload("Company Created \n \n Details: \n \n"+company).build());
		//SubscribeRequest request = new SubscribeRequest(TOPIC_ARN,"email","sundareswar2170906@gmail.com");
		//amazonSNSClient.subscribe(request);
		//PublishRequest publishRequest = new PublishRequest(snsUrl,"New Company Added \n \n Attached details: \n \n"+company, "Company Added");
		//amazonSNSClient.publish(publishRequest);
		return new ResponseEntity<Company>(c, HttpStatus.OK);
	}

	@GetMapping("/api/v1.0/market/company/getall")
	public ResponseEntity<List<Company>> getAll() {
		List<Company> listC = service.getAll();
		return new ResponseEntity<>(listC, HttpStatus.ACCEPTED);
	}

	@GetMapping("/api/v1.0/market/company/info/{companyCode}")
	public ResponseEntity<Company> getCompany(@PathVariable int companyCode) {
		Company c = service.getCompany(companyCode);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}

	@DeleteMapping("/api/v1.0/market/company/delete/{companyCode}")
	public ResponseEntity<Company> deleteCompany(@PathVariable int companyCode) {
		service.deleteCompany(companyCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/ping")
	public String getPing() {
		return "Company ping version : 2.0.7";
	}
}
