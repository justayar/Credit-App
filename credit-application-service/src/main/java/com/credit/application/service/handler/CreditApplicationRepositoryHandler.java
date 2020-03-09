package com.credit.application.service.handler;

import com.credit.application.service.dao.CreditApplication;
import com.credit.application.service.dao.CreditApplicationRepository;
import com.credit.application.service.formbean.CreditApplicationServiceInputBean;
import com.credit.application.service.formbean.CreditApplicationServiceOutputBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class CreditApplicationRepositoryHandler {

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void saveCreditApplicationToRepository(CreditApplicationServiceInputBean creditApplicationServiceInputBean,
                                                   CreditApplicationServiceOutputBean creditApplicationServiceOutputBean){

        logger.info("[(saveCreditApplicationToRepository()] Credit Application will be stored to repository with output params CreditApplicationServiceOutputBean: {}", creditApplicationServiceOutputBean);

        CreditApplication creditApplication = new CreditApplication();
        creditApplication.setApplicationStatus(creditApplicationServiceOutputBean.getApplicationStatus());
        creditApplication.setCreditLimit(creditApplicationServiceOutputBean.getAppliedCreditLimit());
        creditApplication.setIdentityNumber(creditApplicationServiceInputBean.getIdentityNumber());
        creditApplication.setLastApplicationResultDate(new Timestamp(new Date().getTime()));

        logger.info("[(saveCreditApplicationToRepository()] Credit Application will be stored to repository with repository params CreditApplication: {}", creditApplication);

        creditApplicationRepository.saveApplication(creditApplication);

        logger.info("[(saveCreditApplicationToRepository()] Credit Application store to repository called successfully");

    }

    public CreditApplicationServiceOutputBean getCreditApplicationResultFromRepository(String identityNumber){

        logger.info("[(getCreditApplicationResultFromRepository()] Credit Application View will be got from repository for identityNumber: {}", identityNumber);
        CreditApplicationServiceOutputBean creditApplicationServiceOutputBean = new CreditApplicationServiceOutputBean();

        try{

            CreditApplication creditApplication = creditApplicationRepository.findLastApplicationByIdentityNumber(identityNumber);

            logger.info("[(getCreditApplicationResultFromRepository()] Credit Application View got from repository successfully. Response is: {}", creditApplication);

            creditApplicationServiceOutputBean.setAppliedCreditLimit(creditApplication.getCreditLimit());
            creditApplicationServiceOutputBean.setApplicationStatus(creditApplication.getApplicationStatus());

            logger.info("[(getCreditApplicationResultFromRepository()] Credit Application View got from repository called successfully.");

        }catch(EmptyResultDataAccessException ex){
            logger.info("[(getCreditApplicationResultFromRepository()] There is no any credit application exist for identityNumber: {}",identityNumber);
            creditApplicationServiceOutputBean.setApplicationStatus("Not Applied");
        }

        return creditApplicationServiceOutputBean;


    }
}
