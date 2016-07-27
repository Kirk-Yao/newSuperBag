package com.example.k.superbag2.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.k.superbag2.R;
import com.example.k.superbag2.others.Constant;

import java.io.File;
import java.io.IOException;

/**
 * Created by K on 2016/7/26.
 */
public class SettingsLeftFragment extends Fragment{

    private LinearLayout changeHeadLL,changeBgLL,uploadLL,downloadLL,aboutLL,changeSignLL;
    private TextView changeHeadStatus,changeBgStatus;
    private Uri imageUri;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sliding_settings,container,false);
        context = getContext();
        initView(v);
        initListener();
        return v;
    }

    private void initView(View v){
        changeHeadLL = (LinearLayout)v.findViewById(R.id.change_head_ll);
        changeBgLL = (LinearLayout)v.findViewById(R.id.change_bg_ll);
        uploadLL = (LinearLayout)v.findViewById(R.id.upload_ll);
        downloadLL = (LinearLayout)v.findViewById(R.id.download_ll);
        changeHeadStatus = (TextView)v.findViewById(R.id.change_head_status);
        changeBgStatus = (TextView)v.findViewById(R.id.change_bg_status);
        aboutLL = (LinearLayout)v.findViewById(R.id.about_ll);
        changeSignLL = (LinearLayout)v.findViewById(R.id.change_signature_ll);

        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        if (sp.getBoolean("HEAD_CHANGED",false)){
            changeHeadStatus.setText("自定义");
        }
        if (sp.getBoolean("BG_CHANGED",false)){
            changeBgStatus.setText("自定义");
        }

    }

    private void initListener(){
        changeHeadLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFromAlbum(1);
            }
        });
        changeBgLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFromAlbum(2);
            }
        });
        uploadLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        downloadLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        aboutLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"两个大帅比的作品",Toast.LENGTH_SHORT).show();
            }
        });
        changeSignLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSignature();
            }
        });
    }

    private void changeSignature(){
        View v = LayoutInflater.from(context).inflate(R.layout.set_signature,null);
        final EditText signET = (EditText)v.findViewById(R.id.sign_edittext);
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setView(v)
                .setTitle("修改个性签名")

                .setCancelable(true)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = PreferenceManager.
                                getDefaultSharedPreferences(context).edit();
                        String signature = signET.getText().toString().trim();
                        editor.putString(Constant.SIGNATURE,signature);
                        editor.commit();
                        Toast.makeText(context,"修改成功！",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }

    /**
     *  从相册选取,参数用于确定头像还是背景
     *  @param i 头像为1，背景为2
     */
    //在6.0以上有bug,其他未测试
    private void selectFromAlbum(int i){
        createUri(i);
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.putExtra("crop",true);
        intent.putExtra("scale",true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,i);

/*
        try {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
            intent.setType("image");
            intent.addCategory("android.intent.category.OPENABLE");
            startActivityForResult(intent,i);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            try {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image");
                startActivityForResult(intent,i);
            } catch (Exception e2) {
                e.printStackTrace();
            }
        }
*/

       /* if (Build.VERSION.SDK_INT < 19) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("*image*//*");
            startActivityForResult(intent, i);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*image*//*");
            startActivityForResult(intent, i);
        }
*/
    }

    //创建File对象，用于存储选择的照片
    private void createUri(int i){
        File outputImage;
        if (i == 1) {
            outputImage = new File(Environment.getExternalStorageDirectory(), "sbHeadTemp.jpg");
        } else {
            outputImage = new File(Environment.getExternalStorageDirectory(),"sbBGTemp.jpg");
        }
        try{
            if (outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(outputImage);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode != -1) {
                    Log.d("不等于","");
                    return;
                }  else {  //头像
                    //测试
                    imageUri = data.getData();
                    saveData(1);
                    changeHeadStatus.setText("自定义");
                }
                break;
            case 2:
                imageUri = data.getData();
                saveData(2);
                changeBgStatus.setText("自定义");
                break;
        }
    }

    //存储，参数为1代表头像，2代表背景
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void saveData(int i){
        Log.d("uri是  ",imageUri.toString());

        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();
        if (i == 1) {
            editor.putString("headIconUri", imageUri.toString());
            editor.putBoolean("HEAD_CHANGED",true);
            Toast.makeText(context,"头像更换成功！",Toast.LENGTH_SHORT).show();
        } else {
            editor.putString("bgUri",imageUri.toString());
            editor.putBoolean("BG_CHANGED",true);
            Toast.makeText(context,"背景更换成功！",Toast.LENGTH_SHORT).show();
        }
        editor.commit();
    }

}

