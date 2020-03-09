package com.credit.application.service.util;

import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Component
public class TextCapitalizeUtils {

    public String capitalizeFirstLetter(String text){

        if(text == null)
            throw new NullPointerException("String value to capitalize is null");

        if(text.isEmpty())
            throw new InvalidParameterException("String value to capitalize is empty");

        return text.substring(0, 1).toUpperCase() + text.substring(1);

    }
}
