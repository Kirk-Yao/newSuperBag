package com.example.k.superbag2.utils;


import android.annotation.TargetApi;
import android.os.Build;
import android.text.format.Time;

/**
 * Created by K on 2016/6/27.
 * 因为Android月份是从0开始的，0代表一月，所以，得到的月份要+1
 */
@TargetApi(Build.VERSION_CODES.N)
public class GetTime {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;

    private Time t;

    public GetTime(){
        t = new Time();
        t.setToNow();
        year = t.year;
        month = t.month;
        min = t.minute;
        hour = t.hour;
        day = t.monthDay;
    }

    public String getMin() {
        if (min < 10){
            return "0"+min;
        }
        return min+"";
    }

    public String getYear() {
        return year+"";
    }

    public String getMonth() {
        if (month+1 < 10){
            return "0"+(month+1);
        } else
            return month+1+"";
    }

    public String getHour() {
        if (hour < 10){
            return "0"+hour;
        }
        return hour+"";
    }

    public String getDay(){
        if (day < 10){
            return "0"+day;
        }
        return day+"";
    }

    public String getSpecificTime(){
        GetTime gt = new GetTime();
        return gt.getYear()+"-"+gt.getMonth()+"-"+gt.getDay()+"-"+gt.getHour()+":"+gt.getMin();
    }
}
