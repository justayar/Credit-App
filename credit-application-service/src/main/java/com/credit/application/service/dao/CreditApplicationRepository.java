package com.credit.application.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CreditApplicationRepository {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public int saveApplication(CreditApplication creditApplication){

        jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.update(
                "INSERT INTO CreditApplication (identityNumber, applicationStatus,creditLimit,lastApplicationResultDate) VALUES(?,?,?,?)",
                creditApplication.getIdentityNumber(),
                creditApplication.getApplicationStatus(),
                creditApplication.getCreditLimit(),
                creditApplication.getLastApplicationResultDate());

    }

    public CreditApplication findLastApplicationByIdentityNumber(String identityNumber){

        jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.queryForObject(
                "SELECT * FROM CreditApplication WHERE identityNumber = ? ORDER BY lastApplicationResultDate DESC LIMIT 1",
                new Object[]{identityNumber},
                (rs, rowNum) ->
                        new CreditApplication(
                                rs.getString("identityNumber"),
                                rs.getString("applicationStatus"),
                                rs.getDouble("creditLimit"),
                                rs.getTimestamp("lastApplicationResultDate")
                        ));

    }


}
