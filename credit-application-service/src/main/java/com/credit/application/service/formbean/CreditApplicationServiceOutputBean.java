package com.credit.application.service.formbean;

import lombok.Data;

@Data
public class CreditApplicationServiceOutputBean {

    private boolean isCreditApplicationSuccessful;
    private double appliedCreditLimit;

    @Override
    public String toString(){
        return "{ isCreditApplicationSuccessful: "+isCreditApplicationSuccessful+" appliedCreditLimit: "+appliedCreditLimit;
    }
}
