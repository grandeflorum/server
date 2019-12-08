package com.grandeflorum.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    public static List<String> getYearByStartAndEnd(Date startDate,Date endDate) {
        List<String> yearList = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int startYear = c.get(Calendar.YEAR);
        c.setTime(endDate);
        int endYear = c.get(Calendar.YEAR);
        while (startYear <= endYear) {
            yearList.add(String.valueOf(startYear));
            startYear++;
        }
        return yearList;
    }

    public static String DateToString(Date data){

        if(data==null){
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(data);

        return  dateString;
    }

    public static String GmtStringToDate(String str){

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH);
        Date dd = null; //将字符串改为date的格式
        String result ="";
        try {
            str =str.substring(0,str.indexOf("("));
            dd = sdf.parse(str);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            result = formatter.format(dd);
        } catch (ParseException e) {

        }
        return result;
    }

    public static int getDateYear(Date d){

        if(d!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int year = c.get(Calendar.YEAR);
            return year;
        }

        return 0;
    }

    public static Date StringToDate(String date){

        if(StrUtil.isNullOrEmpty(date)){
            return null;
        }

        Date  result = null;

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        try{
            result = format1.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static String getUpDate(String date,String type){

        String result = "";
        if(StrUtil.isNullOrEmpty(date)){
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date now = format.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            switch (type){
                //前一天
                case "Day":
                    c.add(Calendar.DATE, -1);
                    break;
                //前一周
                case "Week":
                    c.add(Calendar.DATE, -7);
                    break;
                //前一月
                case "Month":
                    c.add(Calendar.MONTH, -1);
                    break;
                //前季度
                case "Quarter":
                    c.add(Calendar.MONTH, -3);
                    break;
                //前一年
                case "Year":
                    c.add(Calendar.YEAR, -1);
                    break;

            }

            result= format.format(c.getTime());
        }catch (Exception e){

        }

        return result;
    }


}
