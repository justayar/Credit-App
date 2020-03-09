package com.credit.application.service.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplication {

    private String identityNumber;
    private String applicationStatus;
    private double creditLimit;
    private Timestamp lastApplicationResultDate;


    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Timestamp getLastApplicationResultDate() {
        return lastApplicationResultDate;
    }

    public void setLastApplicationResultDate(Timestamp lastApplicationResultDate) {
        this.lastApplicationResultDate = lastApplicationResultDate;
    }

    @Override
    public String toString(){
        return "{ identityNumber: "+identityNumber+" applicationStatus: "+applicationStatus+" creditLimit: "+creditLimit+" lastApplicationResultDate: "+lastApplicationResultDate;
    }
}
