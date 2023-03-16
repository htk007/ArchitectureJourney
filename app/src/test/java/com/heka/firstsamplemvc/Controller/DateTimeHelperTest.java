package com.heka.firstsamplemvc.Controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DateTimeHelperTest {

    @Test
    public void isDateTimeNotEmpty(){
        String result = DateTimeHelper.getCurrentDateTime();
        boolean expectedResult = result.isEmpty();
        assertEquals(expectedResult, false);
    }
}