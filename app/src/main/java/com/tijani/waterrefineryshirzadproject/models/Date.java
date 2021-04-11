package com.tijani.waterrefineryshirzadproject.models;

import com.alirezaafkar.sundatepicker.components.DateItem;

import java.util.Calendar;
import java.util.Locale;

public class Date extends DateItem {
    public String getDate(){
        Calendar calendar = getCalendar();
        return String.format(Locale.UK,"%d/%d/%d",getYear(),getMonth(),getDay(),calendar.get(Calendar.YEAR)+ calendar.get(Calendar.MONTH)+1+ calendar.get(Calendar.DAY_OF_MONTH));
    }
}
