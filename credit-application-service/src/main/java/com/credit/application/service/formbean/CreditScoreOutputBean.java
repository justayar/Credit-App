package com.credit.application.service.formbean;

import lombok.Data;

@Data
public class CreditScoreOutputBean {

    private String identityNumber;
    private int creditScore;

    @Override
    public String toString(){

        return "{ identityNumber: "+identityNumber+" creditScore: "+creditScore;
    }
}
