package com.credit.score.service;

import com.credit.score.service.formbean.CreditScoreOutputBean;
import com.credit.score.service.mapper.CreditServiceOutputMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreditScoreServiceApplication {

	@Bean
	public CreditScoreOutputBean creditScoreOutputBean(){
		return new CreditScoreOutputBean();
	}


	@Bean
	public CreditServiceOutputMapper creditServiceOutputMapper(){
		return new CreditServiceOutputMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(CreditScoreServiceApplication.class, args);
	}

}
