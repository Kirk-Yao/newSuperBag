package com.example.k.superbag2.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.example.k.superbag2.bean.ItemBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by K on 2016/6/28.
 */
public class SuperbagDatabaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_DB = "create table superbag("
            +"id integer primary key autoincrement,"
            +"tag1 text,"
            +"tag2 text,"
            +"tag3 text,"
            +"content text,"
            +"isMemo blob,"
            +"importance integer,"
            +"oldTime text,"
            +"newTime text,"
            +"pic1 text,"
            +"pic2 text,"
            +"pic3 text,"
            +"pic4 text,"
            +"weather text,"
            +"feelings text"
            +")";

    private static Context cxt;
    private static String name;
    private static SQLiteDatabase.CursorFactory factory;
    private static int version;

    private static SQLiteDatabase db;
    private static SuperbagDatabaseHelper databaseHelper;

    public SuperbagDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        cxt = context;
        this.name = name;
        this.factory = factory;
        this.version = version;
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);

        Log.d("数据库已创建","");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertToDB(String tag1,String tag2,String tag3,
                           String content,boolean isMemo,int importance,
                           String oldTime,String newTime,
                           String pic1,String pic2,String pic3,String pic4,
                           String weather,String feelings){
        Log.d("正在插入数据","...");
        if (db == null){
            getDatabase();
        }

        db.execSQL("insert into superbag(tag1,tag2,tag3,content,isMemo,importance," +
                "oldTime,newTime,pic1,pic2,pic3,pic4,weather,feelings)" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",new String[] {tag1,tag2,tag3,content,isMemo+"",
                importance+"",oldTime,newTime,pic1,pic2,pic3,pic4,weather,feelings});
    }

    private void updateDB(SQLiteDatabase db, ContentValues values){
    }

    private void deleteDB(){

    }


    /**
     * 返回所有数据的列表，数据过多后，可以一次返回十条等
     * 默认为按照最新时间返回
     */
    public List<ItemBean> queryBD(){
        if (db == null){
            getDatabase();
        }
        Cursor cursor = db.query("superbag",null,null,null,null,null,null);
        List<ItemBean> list = new ArrayList<>();
        Log.d("长度是",cursor.getCount()+"");
        if (cursor.moveToNext()){
            do {
                ItemBean item = getContent(cursor);
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Collections.reverse(list);
        return list;
    }

    /**
     * 根据固定行号查询
     */
    public static ItemBean queryBD(int lineIndex){
        if (db == null){
            getDatabase();
        }
        ItemBean item = null;
        Cursor cursor = db.query("superbag",null, String.valueOf(lineIndex),null,null,null,null);
        Log.d("cursor长度",cursor.getColumnCount()+"");

        if (cursor.moveToPosition(lineIndex)){
            item = getContent(cursor);
        }
        cursor.close();
        return item;
    }

    private static ItemBean getContent(Cursor cursor){

        String tag1 = cursor.getString(cursor.getColumnIndex("tag1"));
        String tag2 = cursor.getString(cursor.getColumnIndex("tag2"));
        String tag3 = cursor.getString(cursor.getColumnIndex("tag3"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        String oldTime = cursor.getString(cursor.getColumnIndex("oldTime"));
        String isMemo = cursor.getString(cursor.getColumnIndex("isMemo"));
        String pic1 = cursor.getString(cursor.getColumnIndex("pic1"));
        String pic2 = cursor.getString(cursor.getColumnIndex("pic2"));
        String pic3 = cursor.getString(cursor.getColumnIndex("pic3"));
        String pic4 = cursor.getString(cursor.getColumnIndex("pic4"));
        String weather = cursor.getString(cursor.getColumnIndex("weather"));
        String feelings = cursor.getString(cursor.getColumnIndex("feelings"));

        Log.d("content 是",content);
        Log.d("是否备忘",isMemo);
        Log.d("旧时间是",oldTime);
        //---------------
        List<Uri> picList = new ArrayList<>();
        if (pic1 != null) {
            Uri uri1 = Uri.parse(pic1);
            picList.add(uri1);
        }
        if (pic2 != null){
            Uri uri2 = Uri.parse(pic2);
            picList.add(uri2);
        }
        if (pic3 != null){
            Uri uri3 = Uri.parse(pic3);
            picList.add(uri3);
        }
        if (pic4 != null){
            Uri uri4 = Uri.parse(pic4);
            picList.add(uri4);
        }

        ItemBean item = new ItemBean(tag1,tag2,tag3,content,isMemo,1,oldTime,"2020",picList,weather,feelings);
        return item;
    }

    public static SQLiteDatabase getDatabase(){
        //----------
        databaseHelper = new SuperbagDatabaseHelper(cxt,name,factory,version);
        db = databaseHelper.getWritableDatabase();
        return db;
    }

}
