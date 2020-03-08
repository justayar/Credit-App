package com.credit.score.service.mapper;

import com.credit.score.service.formbean.CreditScoreOutputBean;
import org.springframework.beans.factory.annotation.Autowired;

public class CreditServiceOutputMapper {

    @Autowired
    private CreditScoreOutputBean creditScoreOutputBean;

    public CreditScoreOutputBean mapToCreditScoreOutputBean(String identityNumber,int creditScore){

        creditScoreOutputBean.setCreditScore(creditScore);
        creditScoreOutputBean.setIdentityNumber(identityNumber);

        return creditScoreOutputBean;
    }
}
