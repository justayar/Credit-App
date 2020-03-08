package com.credit.application.service.formbean;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreditApplicationServiceInputBean {

    @NotNull
    @Length(min=11,max=11)
    private String identityNumber;

    @NotNull
    private String customerName;

    @NotNull
    private String customerSurname;

    private double monthlyIncome;

    @NotNull
    @Size(min=10,max=10)
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    @Override
    public String toString(){
        return "{ identityNumber: "+identityNumber+" customerNameSurname: "+customerName+" "
                +customerSurname+" monthlyIncome: "+monthlyIncome+" phoneNumber: "+phoneNumber;
    }

}
