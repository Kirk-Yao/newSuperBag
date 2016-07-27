package com.example.k.superbag2.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.FileNotFoundException;

/**
 * Created by K on 2016/7/8.
 */
public class GetImageUtils {

    //根据Uri得到图片,用于保存在sharedPreference中的
    //有bug
    public static Bitmap getBMFromUri(Context context,String name){
        Uri uri = getUri(context,name);
        Log.d("得到的图片uri是--",name);
        Bitmap bitmap = null;
        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                getContext().getContentResolver().takePersistableUriPermission(uri,takeFlags);
//            }
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Uri getUri(Context context,String name){
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(context);
        String head = pref.getString(name,"no");
        Uri headUri = Uri.parse(head);
        Log.d(name+"是--",head);
        return headUri;
    }

    public static Bitmap getBMFromUri(Context context,Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("","图片uri错误");
        }
        return bitmap;
    }
}
