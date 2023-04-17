package com.stockmarket.fse.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClient;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
public class AwsSQSConfig {
	
	@Value("${cloud.aws.region.static}")
	private String region;
	
	@Value("${cloud.aws.credentials.access-key}")
	private String awsAccessKey;
	
	@Value("${cloud.aws.credentials.secret-key}")
	private String awsSecretKey;
	
	@Bean
	public QueueMessagingTemplate queueMessagingTemplate() {
		return new QueueMessagingTemplate(amazonSqsAsync());
	}

	@Bean
	@Primary
	public AmazonSQSAsync amazonSqsAsync() {
		return AmazonSQSAsyncClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey))).build();
	}
	
//	public AmazonSNSAsync getAmazonSNSClient() {
//		return AmazonSNSAsyncClientBuilder.standard().withRegion("ap-south-1").withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey,awsSecretKey))).build();
//	}
}
