package com.credit.application.service.handler;

import com.credit.application.service.extcall.CreditScoreService;
import com.credit.application.service.formbean.CreditApplicationServiceInputBean;
import com.credit.application.service.formbean.CreditApplicationServiceOutputBean;
import com.credit.application.service.formbean.CreditScoreOutputBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import javax.naming.ServiceUnavailableException;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditApplicationServiceHandlerTest {

    @InjectMocks
    public CreditApplicationServiceHandler creditApplicationServiceHandler;

    private CreditApplicationServiceInputBean creditApplicationServiceInputBean;

    private CreditScoreOutputBean creditScoreOutputBean;

    @Mock
    public CreditScoreService creditScoreService;

    @Mock
    public CreditApplicationRepositoryHandler creditApplicationRepositoryHandler;

    @Mock
    public CreditApplicationSmsHandler creditApplicationSmsHandler;

    @Before
    public void setUp(){

        creditApplicationServiceInputBean = new CreditApplicationServiceInputBean();
        creditScoreOutputBean = new CreditScoreOutputBean();
        creditScoreOutputBean.setIdentityNumber("10010010100");
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation){
                return 1;
            }
        }).when(creditApplicationSmsHandler).sendSmsNotificationToCustomer(any(),any());

    }

    @Test(expected = ServiceUnavailableException.class)
    public void throwExceptionWhenCreditScoreIsNull() throws Exception {

        when(creditScoreService.getCreditScore(any())).thenReturn(null);

        creditApplicationServiceHandler.handleCreditApplication(creditApplicationServiceInputBean);
    }

    @Test
    public void returnRejectedApplicationStatusWhenCreditScoreIsBelowThan500() throws Exception {

        creditScoreOutputBean.setCreditScore(400);
        when(creditScoreService.getCreditScore(any())).thenReturn(creditScoreOutputBean);

        CreditApplicationServiceOutputBean creditApplicationServiceOutputBean = creditApplicationServiceHandler.handleCreditApplication(creditApplicationServiceInputBean);

        assertEquals("Rejected",creditApplicationServiceOutputBean.getApplicationStatus());

    }

    @Test
    public void returnRejectedApplicationStatusWhenCreditScoreIsUpperThan500ButIncomeBiggerThan5000() throws Exception {

        creditScoreOutputBean.setCreditScore(700);
        creditApplicationServiceInputBean.setMonthlyIncome(7000);
        when(creditScoreService.getCreditScore(any())).thenReturn(creditScoreOutputBean);

        CreditApplicationServiceOutputBean creditApplicationServiceOutputBean = creditApplicationServiceHandler.handleCreditApplication(creditApplicationServiceInputBean);

        assertEquals("Rejected",creditApplicationServiceOutputBean.getApplicationStatus());

    }

    @Test
    public void returnAcceptedApplicationStatusWhenCreditScoreIsUpperThan500ButIncomeBelowThan5000() throws Exception {

        creditScoreOutputBean.setCreditScore(700);
        creditApplicationServiceInputBean.setMonthlyIncome(4000);
        when(creditScoreService.getCreditScore(any())).thenReturn(creditScoreOutputBean);

        CreditApplicationServiceOutputBean creditApplicationServiceOutputBean = creditApplicationServiceHandler.handleCreditApplication(creditApplicationServiceInputBean);

        assertEquals("Accepted",creditApplicationServiceOutputBean.getApplicationStatus());

    }

    @Test
    public void returnAcceptedApplicationStatusWhenCreditScoreIsUpperThan1000() throws Exception {

        creditScoreOutputBean.setCreditScore(1100);
        when(creditScoreService.getCreditScore(any())).thenReturn(creditScoreOutputBean);

        CreditApplicationServiceOutputBean creditApplicationServiceOutputBean = creditApplicationServiceHandler.handleCreditApplication(creditApplicationServiceInputBean);

        assertEquals("Accepted",creditApplicationServiceOutputBean.getApplicationStatus());

    }
}