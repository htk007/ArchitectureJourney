package com.heka.firstsamplemvc.Controller;

import com.heka.firstsamplemvc.Model.TimeConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

    public static String getCurrentDateTime(){
        LocalDateTime now = LocalDateTime.now();
        return formatDateTime(TimeConstants.FORMAT_YYYYMMDD_hhmmss,now);
    }

    public static String formatDateTime(String pattern, LocalDateTime time){
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return datetimeFormatter.format(time);
    }
}
