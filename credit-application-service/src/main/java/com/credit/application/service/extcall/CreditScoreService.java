package com.credit.application.service.extcall;

import com.credit.application.service.formbean.CreditScoreOutputBean;

public interface CreditScoreService {

    CreditScoreOutputBean getCreditScore(String identityNumber);

}
