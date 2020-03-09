package com.credit.application.service.handler;

import com.credit.application.service.constants.CreditApplicationConstants;
import com.credit.application.service.extcall.CreditScoreService;
import com.credit.application.service.formbean.CreditApplicationServiceInputBean;
import com.credit.application.service.formbean.CreditApplicationServiceOutputBean;
import com.credit.application.service.formbean.CreditScoreOutputBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.naming.ServiceUnavailableException;


@Component
public class CreditApplicationServiceHandler {

    @Autowired
    private CreditScoreService creditScoreService;

    @Autowired
    private CreditApplicationRepositoryHandler creditApplicationRepositoryHandler;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public CreditApplicationServiceOutputBean handleCreditApplication(CreditApplicationServiceInputBean creditApplicationServiceInputBean) throws Exception {

        CreditScoreOutputBean creditScore = creditScoreService.getCreditScore(creditApplicationServiceInputBean.getIdentityNumber());

        logger.info("[(handleCreditApplication()] Credit Score service called successfully. Response is : {}", creditScore);

        if(creditScore != null){

            CreditApplicationServiceOutputBean creditApplicationServiceOutputBean = evaluateCreditApplication(creditApplicationServiceInputBean, creditScore.getCreditScore());

            creditApplicationRepositoryHandler.saveCreditApplicationToRepository(creditApplicationServiceInputBean,creditApplicationServiceOutputBean);

            return creditApplicationServiceOutputBean;

        }else{
            throw new ServiceUnavailableException("Credit score service is not available right now. Please try again.");
        }

    }

    private CreditApplicationServiceOutputBean evaluateCreditApplication(CreditApplicationServiceInputBean creditApplicationServiceInputBean,double creditScore){

        CreditApplicationServiceOutputBean creditApplicationServiceOutputBean = new CreditApplicationServiceOutputBean();

        if(creditScore < CreditApplicationConstants.UPPER_LIMIT_CREDIT_SCORE_FOR_HIGH_RISK){
            creditApplicationServiceOutputBean.setApplicationStatus("Rejected");
        }else if(creditScore < CreditApplicationConstants.UPPER_LIMIT_CREDIT_SCORE_FOR_MEDIUM_RISK){
            handleMediumRiskLevelCreditApplication(creditApplicationServiceInputBean, creditApplicationServiceOutputBean);
        }else{
            creditApplicationServiceOutputBean.setApplicationStatus("Accepted");
            creditApplicationServiceOutputBean.setAppliedCreditLimit(creditApplicationServiceInputBean.getMonthlyIncome()*CreditApplicationConstants.CREDIT_LIMIT_MULTIPLIER);
        }

        return creditApplicationServiceOutputBean;

    }

    private void handleMediumRiskLevelCreditApplication(CreditApplicationServiceInputBean creditApplicationServiceInputBean, CreditApplicationServiceOutputBean creditApplicationServiceOutputBean) {
        if(creditApplicationServiceInputBean.getMonthlyIncome() < CreditApplicationConstants.MIN_MONTHLY_INCOME_LIMIT_FOR_CREDIT){
            creditApplicationServiceOutputBean.setApplicationStatus("Accepted");
            creditApplicationServiceOutputBean.setAppliedCreditLimit(CreditApplicationConstants.APPLIED_CREDIT_LIMIT_FOR_MEDIUM_RISK);
        }else{
            creditApplicationServiceOutputBean.setApplicationStatus("Rejected");
        }
    }

    public CreditApplicationServiceOutputBean handleViewCreditApplication(String identityNumber){

        return creditApplicationRepositoryHandler.getCreditApplicationResultFromRepository(identityNumber);
    }


}
