package com.credit.application.service.util;

import org.junit.Before;
import org.junit.Test;
import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class TextCapitalizeUtilsTest {

    private TextCapitalizeUtils textCapitalizeUtils;

    @Before
    public void init() {
        textCapitalizeUtils = new TextCapitalizeUtils();
    }

    @Test(expected = NullPointerException.class)
    public void throwNullPointerExceptionWhenTextIsNull(){

        textCapitalizeUtils.capitalizeFirstLetter(null);
    }

    @Test(expected = InvalidParameterException.class)
    public void throwInvalidParameterExceptionWhenTextIsNull(){

        textCapitalizeUtils.capitalizeFirstLetter("");
    }

    @Test
    public void returnCapitalizedWhenTextIsNotNullAndEmpty(){

        String capitalizedText = textCapitalizeUtils.capitalizeFirstLetter("capitalize");

        assertEquals("Capitalize",capitalizedText);
    }

}