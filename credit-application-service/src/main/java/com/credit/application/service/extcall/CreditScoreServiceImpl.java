package com.credit.application.service.extcall;

import com.credit.application.service.formbean.CreditScoreOutputBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class CreditScoreServiceImpl implements CreditScoreService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${creditScoreServiceUrl}")
    private String CREDIT_SCORE_SERVICE_URL;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public CreditScoreOutputBean getCreditScore(String identityNumber) {

        logger.info("[(getCreditScore()] Credit score service will be called.");


        restTemplate = new RestTemplate();

        try {

            return restTemplate.getForObject(
                    CREDIT_SCORE_SERVICE_URL+"?identityNumber="+identityNumber,
                    CreditScoreOutputBean.class);

        } catch(HttpStatusCodeException ex){
            logger.warn("[(getCreditScore()] Credit score service get error. Exception Response Body is {}",ex.getResponseBodyAsString());
            return null;
        } catch(RestClientException e){
            logger.warn("[(getCreditScore()] Credit score service get error. Exception Message is {}",e.getMessage());
            return null;
        }
    }
}
