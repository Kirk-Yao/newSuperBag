package com.example.k.superbag2.others;

/**
 * Created by K on 2016/6/26.
 */
public class ListItem {
    public static final int TYPE_NO_PIC = 0;
    public static final int TYPE_ONE_PIC = 1;
    public static final int TYPE_TWO_PIC = 2;

    public static final int TYPE_ALL_COUNT = 3;


    private String typeName;
    private int type;

    public ListItem(String name,int type){
        typeName = name;
        this.type = type;
    }

    public String getTypeName(){
        return typeName;
    }

    public int getType(){
        return type;
    }
}
