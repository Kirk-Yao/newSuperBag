package com.example.k.superbag2.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.example.k.superbag2.R;
import com.example.k.superbag2.bean.ItemBean;
import com.example.k.superbag2.others.Constant;
import com.example.k.superbag2.others.ListItem;
import com.example.k.superbag2.utils.GetImageUtils;
import com.example.k.superbag2.utils.SuperbagDatabaseHelper;

import java.util.List;

/**
 * Created by K on 2016/7/26.
 */
public class PreviewActivity extends Activity implements View.OnClickListener{

    private Button preBack,preEdit;
    private LinearLayout preBackLL,preEditLL;
    private ImageView preHeadIcon;
    private TextView preMonth,preWeather,preFeelings,preTag1,preTag2,preContent,preMin;
    private ImageView prePic1,prePic2,prePic3,prePic4;

    private int lineNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        initView();
        setListener();
        initData();
    }

    private void initView(){
        preBack = (Button)findViewById(R.id.pre_back);
        preEdit = (Button)findViewById(R.id.pre_edit);
        preBackLL = (LinearLayout)findViewById(R.id.pre_back_ll);
        preEditLL = (LinearLayout)findViewById(R.id.pre_edit_ll);
        preHeadIcon = (ImageView)findViewById(R.id.pre_head_icon);
        preMonth = (TextView)findViewById(R.id.pre_month);
        preWeather = (TextView)findViewById(R.id.pre_weather_TV);
        preFeelings = (TextView)findViewById(R.id.pre_feelings_TV);
        preTag1 = (TextView)findViewById(R.id.pre_tag2);
        preTag2 = (TextView)findViewById(R.id.pre_tag3);
        preContent = (TextView)findViewById(R.id.pre_content);
        preMin = (TextView)findViewById(R.id.pre_min);
        prePic1 = (ImageView)findViewById(R.id.pre_iv1);
        prePic2 = (ImageView) findViewById(R.id.pre_iv2);
        prePic3 = (ImageView)findViewById(R.id.pre_iv3);
        prePic4 = (ImageView)findViewById(R.id.pre_iv4);

        //设置头像
        Bitmap head = GetImageUtils.getBMFromUri(this,Constant.HEAD_ICON_URI);
        if (head != null){
            preHeadIcon.setImageBitmap(head);
        }
    }

    private void setListener(){
        preBack.setOnClickListener(this);
        preEdit.setOnClickListener(this);
        preBackLL.setOnClickListener(this);
        preEditLL.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pre_back:
            case R.id.pre_back_ll:
                finish();
                break;
            case R.id.pre_edit:
            case R.id.pre_edit_ll:
                Intent intent = new Intent(PreviewActivity.this,EditActivity.class);
                intent.putExtra(Constant.LINE_INDEX,lineNum);
                startActivity(intent);
                break;
        }
    }

    private void initData(){
        Intent intent = getIntent();
        lineNum = intent.getIntExtra(Constant.LINE_INDEX,-1);
        if (lineNum != -1){
            ItemBean item = SuperbagDatabaseHelper.queryBD(lineNum);
            if (item == null) {
                Log.d("item为空", "");
                Toast.makeText(this,"wei",Toast.LENGTH_SHORT).show();
                return;
            }
           /* if (item.getContent() != null) {
                preContent.setText(item.getContent());
            }*/
            if (!item.getTag2().equals("")){
                preTag1.setVisibility(View.VISIBLE);
                preTag1.setText(item.getTag2());
            }
            if (!item.getTag3().equals("")){
                preTag2.setVisibility(View.VISIBLE);
                preTag2.setText(item.getTag3());
            }
            String month = item.getOldTime().substring(0,10);
            preMonth.setText(month);
            String min = item.getOldTime().substring(11,item.getOldTime().length());
            preMin.setText(min);
            //设置图片
            /*List<Uri> picList = item.getPicList();
            if (!picList.get(0).toString().equals("")){
                Glide.with(this)
                        .load(picList.get(0))
                        .into(prePic1);
            }
            if (!picList.get(1).toString().equals("")){
                Glide.with(this)
                        .load(picList.get(1))
                        .into(prePic2);
            }
            if (!picList.get(2).toString().equals("")){
                Glide.with(this)
                        .load(picList.get(2))
                        .into(prePic3);
            }
            if (!picList.get(3).toString().equals("")){
                Glide.with(this)
                        .load(picList.get(3))
                        .into(prePic4);
            }*/
        }
    }
}
