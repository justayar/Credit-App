package com.credit.application.service.controller;

import com.credit.application.service.formbean.CreditApplicationServiceInputBean;
import com.credit.application.service.formbean.CreditApplicationServiceOutputBean;
import com.credit.application.service.handler.CreditApplicationServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditApplicationServiceController {

    @Autowired
    private CreditApplicationServiceHandler creditApplicationServiceHandler;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(value = "/applyCreditApplication")
    public void applyCreditApplication(CreditApplicationServiceInputBean creditApplicationServiceInputBean) throws Exception {

        logger.info("[(applyCreditApplication()] Credit Application Service called with parameters CreditApplicationServiceInputBean: {}", creditApplicationServiceInputBean);

        CreditApplicationServiceOutputBean creditApplicationServiceOutputBean = creditApplicationServiceHandler.handleCreditApplication(creditApplicationServiceInputBean);

        logger.info("[(applyCreditApplication()] Credit Application Service called successfully. Response is {}",creditApplicationServiceOutputBean);

    }

    @GetMapping(value = "/viewCreditApplication")
    public CreditApplicationServiceOutputBean viewCreditApplication(String identityNumber){

        logger.info("[(viewCreditApplication()] Credit Application View Service called with identityNumber: {}", identityNumber);

        CreditApplicationServiceOutputBean creditApplicationServiceOutputBean = creditApplicationServiceHandler.handleViewCreditApplication(identityNumber);

        logger.info("[(viewCreditApplication()] Credit Application Service called successfully. Response is {}",creditApplicationServiceOutputBean);

        return creditApplicationServiceOutputBean;
    }




}

