package com.credit.application.service.formbean;

import lombok.Data;

@Data
public class CreditApplicationServiceOutputBean {

    private String applicationStatus;
    private double appliedCreditLimit;

    @Override
    public String toString(){
        return "{ applicationStatus: "+applicationStatus+" appliedCreditLimit: "+appliedCreditLimit;
    }
}
