package com.example.hopeart.Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityMethods
{
    public static String getDateAndTime()
    {
        DateFormat df=new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date date=new Date();
        return df.format(date);
    }
}
