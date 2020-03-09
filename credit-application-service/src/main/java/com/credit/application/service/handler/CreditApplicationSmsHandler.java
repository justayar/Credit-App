package com.credit.application.service.handler;

import com.credit.application.service.constants.CreditApplicationConstants;
import com.credit.application.service.formbean.CreditApplicationServiceInputBean;
import com.credit.application.service.formbean.CreditApplicationServiceOutputBean;
import com.credit.application.service.util.TextCapitalizeUtils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditApplicationSmsHandler {

    @Autowired
    private TextCapitalizeUtils textCapitalizeUtils;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public void sendSmsNotificationToCustomer(CreditApplicationServiceInputBean creditApplicationServiceInputBean,
                                              CreditApplicationServiceOutputBean creditApplicationServiceOutputBean){

        logger.info("[(sendSmsNotificationToCustomer()] Sms notification will be send to customer with phone number is {}", creditApplicationServiceInputBean.getPhoneNumber());


        String resultSms = createSmsMessage(creditApplicationServiceInputBean, creditApplicationServiceOutputBean);

        logger.info("[(sendSmsNotificationToCustomer()] Sms notification will be send to customer with Message is  {}", resultSms);

        Twilio.init(CreditApplicationConstants.TWILIO_SMS_ACCOUNT_ID, CreditApplicationConstants.TWILIO_SMS_AUTH_TOKEN);
        Message.creator(
                new PhoneNumber(creditApplicationServiceInputBean.getPhoneNumber()),
                new PhoneNumber(CreditApplicationConstants.TWILIO_PHONE_NUMBER),
                resultSms)
                .create();

        logger.info("[(sendSmsNotificationToCustomer()] Sms notification send to customer successfully");

    }

    private String createSmsMessage(CreditApplicationServiceInputBean creditApplicationServiceInputBean,
                                    CreditApplicationServiceOutputBean creditApplicationServiceOutputBean){

        String customerName = textCapitalizeUtils.capitalizeFirstLetter(creditApplicationServiceInputBean.getCustomerName());

        String customerSurname = textCapitalizeUtils.capitalizeFirstLetter(creditApplicationServiceInputBean.getCustomerSurname());
        StringBuilder message = new StringBuilder();

        if(creditApplicationServiceOutputBean.getApplicationStatus().equalsIgnoreCase("Accepted")){
            message.append(customerName);
            message.append(" ");
            message.append(customerSurname);
            message.append(" kredi başvurunuz onaylanmıştır.");
            message.append("Hesabınıza "+creditApplicationServiceOutputBean.getAppliedCreditLimit()+" TL lik krediniz en yakın zamanda yatırılacaktır.");
        }else{
            message.append(customerName);
            message.append(" ");
            message.append(customerSurname);
            message.append(" kredi başvurunuz onaylanmamıştır.");
        }

        return message.toString();
    }


}
