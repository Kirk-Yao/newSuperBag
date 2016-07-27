package com.example.k.superbag2.bean;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.util.List;

/**
 * Created by K on 2016/6/26.
 */
public class ItemBean {

    private String tag1,tag2,tag3;
    private String content;
    private String oldTime;
    private String newTime;
    private List<Drawable> drawableList;
    private String dayTime;
    private String isMemo;
    private String weather;
    private String feelings;
    private int importance;

    private List<Uri> picList;

    public ItemBean(String tag1, String tag2, String tag3, String content, String isMemo,
                    int importance, String oldTime, String newTime, List<Uri> picList,
                    String weather, String feelings){
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.content = content;
        this.isMemo = isMemo;
        this.importance = importance;
        this.oldTime = oldTime;
        this.newTime = newTime;
        this.picList = picList;
        this.weather = weather;
        this.feelings = feelings;
    }

    public ItemBean(){}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOldTime() {
        return oldTime;
    }

    public void setOldTime(String oldTime) {
        this.oldTime = oldTime;
    }

    public String getNewTime() {
        return newTime;
    }

    public void setNewTime(String newTime) {
        this.newTime = newTime;
    }

    public List<Drawable> getDrawableList() {
        return drawableList;
    }

    public void setDrawableList(List<Drawable> drawableList) {
        this.drawableList = drawableList;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getIsMemo() {
        return isMemo;
    }

    public void setIsMemo(String isMemo) {
        this.isMemo = isMemo;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getPicNum() {
        return picList.size();
    }

    public List<Uri> getPicList(){
        return picList;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getFeelings() {
        return feelings;
    }

    public void setFeelings(String feelings) {
        this.feelings = feelings;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
