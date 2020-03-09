# Credit-App

This application is a simple design of credit application service.

## Requirements

For building and running the application you need:

- [JDK 1.10](https://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html)
- [Maven 4](https://maven.apache.org)
- [Lombok](https://projectlombok.org/)
    * For IntelliJ [Lombok IntelliJ Plugin] (https://plugins.jetbrains.com/plugin/6317-lombok)
    * For Eclipse [Lombok Eclipse] (https://projectlombok.org/downloads/lombok.jar)
    
For Twilio sms service, you have to sign up [Twilio](https://www.twilio.com)

   - Get AccountId, AuthToken and PhoneNumber from Twilio Dashboard.
   - Go to com.credit.application.service.constants.CreditApplicationConstants file and fill below constants with Twilio keys.
      
       public static final String TWILIO_SMS_ACCOUNT_ID = "*******";
       public static final String TWILIO_SMS_AUTH_TOKEN = "*******";
       public static final String TWILIO_PHONE_NUMBER = "******";

