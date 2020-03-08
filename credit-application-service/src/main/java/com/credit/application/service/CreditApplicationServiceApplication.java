package com.credit.application.service;

import com.credit.application.service.dao.CreditApplicationRepository;
import com.credit.application.service.extcall.CreditScoreService;
import com.credit.application.service.extcall.CreditScoreServiceImpl;
import com.credit.application.service.formbean.CreditApplicationServiceInputBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CreditApplicationServiceApplication {

	@Bean
	public CreditApplicationServiceInputBean applyCreditServiceInputBean(){
		return new CreditApplicationServiceInputBean();
	}

	@Bean
	public CreditScoreService creditScoreService(){
		return new CreditScoreServiceImpl();
	}

	@Bean
	public RestTemplate restTemplate(){ return new RestTemplate();}


	@Autowired
	@Qualifier("creditApplicationRepository")
	private CreditApplicationRepository creditApplicationRepository;

	public static void main(String[] args) {
		SpringApplication.run(CreditApplicationServiceApplication.class, args);
	}

}
