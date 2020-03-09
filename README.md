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
      
      ```java
       public static final String TWILIO_SMS_ACCOUNT_ID = "*******";
       public static final String TWILIO_SMS_AUTH_TOKEN = "*******";
       public static final String TWILIO_PHONE_NUMBER = "******";
      ```
 For mysql database integration, you have to add below mysql command on your local mysql server.
 
  - You have to create user in your local server with as below:
  
         spring.datasource.url=jdbc:mysql://localhost:3306/credit
         spring.datasource.username=test
         spring.datasource.password=Test1234

       
        CREATE DATABASE `credit` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT E             ENCRYPTION='N' */;

        CREATE TABLE "CreditApplication" (
        'identityNumber' varchar(180) NOT NULL,
        'applicationStatus' varchar(120) NOT NULL,
        'creditLimit' decimal(10,2) NOT NULL,
        'lastApplicationResultDate' datetime NOT NULL
        )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
       
      
