package com.example.baji.Utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class Utils {

    public static String getDateFromTimeStamp(String time){
        long getTime=Long.parseLong(time);
        Timestamp timestamp = new Timestamp(getTime);
        Date date = new Date(timestamp.getTime());
        String dateStr=null;
        SimpleDateFormat simpleDateFormat;
        if(date != null){
            simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
            dateStr=simpleDateFormat.format(date);
        }

        return dateStr;
    }

    public static String getAmountFormate(int amount){
        String amt="₹ "+String.valueOf(amount);
        return amt;
    }

    public static String getOrderId(){
        String orderId="ORDER"+uniqId();
        return orderId;
    }

    private static String uniqId() {
        Timestamp ts = new Timestamp(new Date().getTime());
        ts.getTime();
        Random random = new Random();

        String id=String.valueOf(random.nextInt(100))+String.valueOf(ts.getTime())+
                String.valueOf(random.nextInt(100));
        return id;
    }
}
