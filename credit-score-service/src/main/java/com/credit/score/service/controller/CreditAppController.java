package com.credit.score.service.controller;

import com.credit.score.service.constants.ApplicationConstants;
import com.credit.score.service.formbean.CreditScoreOutputBean;
import com.credit.score.service.mapper.CreditServiceOutputMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class CreditAppController {

    @Autowired
    private CreditServiceOutputMapper creditServiceOutputMapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(value = "/getCreditScore")
    public CreditScoreOutputBean getCreditScore(@RequestParam String identityNumber) {

        logger.info("[(getCreditScore()] Credit Score handler called with IdentityNumber: {}",identityNumber);

        Random randomGenerator = new Random();

        int randomCreditScore = randomGenerator.nextInt((ApplicationConstants.MAX_CREDIT_SCORE - ApplicationConstants.MIN_CREDIT_SCORE) + 1) + ApplicationConstants.MIN_CREDIT_SCORE;

        logger.info("[(getCreditScore()] Credit Score handler called successfully. Random Credit Score is : {}",randomCreditScore);

        return creditServiceOutputMapper.mapToCreditScoreOutputBean(identityNumber, randomCreditScore);
    }


}
