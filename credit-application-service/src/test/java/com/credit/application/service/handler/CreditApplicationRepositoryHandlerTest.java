package com.credit.application.service.handler;

import com.credit.application.service.dao.CreditApplicationRepository;
import com.credit.application.service.formbean.CreditApplicationServiceInputBean;
import com.credit.application.service.formbean.CreditApplicationServiceOutputBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditApplicationRepositoryHandlerTest {

    @InjectMocks
    private CreditApplicationRepositoryHandler creditApplicationRepositoryHandler;

    @Mock
    private CreditApplicationRepository creditApplicationRepository;

    private CreditApplicationServiceInputBean creditApplicationServiceInputBean;

    private CreditApplicationServiceOutputBean creditApplicationServiceOutputBean;


    @Before
    public void setUp(){
        creditApplicationServiceInputBean = new CreditApplicationServiceInputBean();
        creditApplicationServiceOutputBean = new CreditApplicationServiceOutputBean();
    }

    @Test
    public void successfullTest(){

        when(creditApplicationRepository.saveApplication(any())).thenReturn(1);

        creditApplicationRepositoryHandler.saveCreditApplicationToRepository(creditApplicationServiceInputBean,creditApplicationServiceOutputBean);


    }

    @Test
    public void failTest(){

        when(creditApplicationRepository.saveApplication(any())).thenReturn(0);

        creditApplicationRepositoryHandler.saveCreditApplicationToRepository(creditApplicationServiceInputBean,creditApplicationServiceOutputBean);

    }


}