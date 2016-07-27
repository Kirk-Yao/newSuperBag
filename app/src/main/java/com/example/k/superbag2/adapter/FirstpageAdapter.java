package com.example.k.superbag2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.k.superbag2.R;
import com.example.k.superbag2.bean.ItemBean;
import com.example.k.superbag2.others.ListItem;
import com.example.k.superbag2.utils.GetImageUtils;

import java.util.List;


/**
 * Created by K on 2016/6/26.
 */
public class FirstpageAdapter extends ArrayAdapter<ItemBean> {

    private List<ItemBean> itemBeanList;

    public FirstpageAdapter(Context context, int resource, List<ItemBean> objects) {
        super(context, resource, objects);
        itemBeanList = objects;
    }

    @Override
    public int getCount() {
        return itemBeanList.size();
    }

    @Override
    public ItemBean getItem(int position) {
        return itemBeanList.get(position);
    }

    //根据图片数量的不同加载不同的布局
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //------------
        ItemBean itemBean = getItem(position);
        int type = getItemViewType(position);
        Log.d("类型是",type+"");
        switch (type){
            case ListItem.TYPE_NO_PIC:
                Pic0ViewHolder holder0 = null;
                if (convertView == null){
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_firstpage_0pic,null);
                    holder0  = new Pic0ViewHolder();
                    holder0.time = (TextView) convertView.findViewById(R.id.fp_0pic_time);
                    holder0.content = (TextView)convertView.findViewById(R.id.fp_0pic_content);
                    holder0.oldTime = (TextView)convertView.findViewById(R.id.fp_0pic_oldTime);
                    holder0.newTime = (TextView)convertView.findViewById(R.id.fp_0pic_newTime);
                    holder0.tag1 = (TextView)convertView.findViewById(R.id.fp_0pic_tag1);
                    holder0.tag2 = (TextView)convertView.findViewById(R.id.fp_0pic_tag2);
                    holder0.tag3 = (TextView)convertView.findViewById(R.id.fp_0pic_tag3);
                    holder0.weather = (TextView)convertView.findViewById(R.id.fp_0pic_weather);
                    holder0.feelings = (TextView)convertView.findViewById(R.id.fp_0pic_feelings);
                    convertView.setTag(holder0);
                } else {
                    holder0 = (Pic0ViewHolder) convertView.getTag();
                }
                //-----------------
                //具体逻辑处理
                if (itemBean.getIsMemo().equals("true")) {
                    holder0.tag1.setText("备忘");
                } else {
                    holder0.tag1.setText("日记");
                }
                if (itemBean.getTag2().equals("")){
                    holder0.tag2.setVisibility(View.GONE);
                } else {
                    holder0.tag2.setText(itemBean.getTag2());
                }
                if (itemBean.getTag3().equals("")){
                    holder0.tag3.setVisibility(View.GONE);
                } else {
                    holder0.tag3.setText(itemBean.getTag3());
                }

                holder0.content.setText(itemBean.getContent());
                holder0.oldTime.setText(itemBean.getOldTime());
                holder0.weather.setText(itemBean.getWeather());
                holder0.feelings.setText(itemBean.getFeelings());
                Log.d("执行到","没有图片的adapter");
                break;
            case ListItem.TYPE_ONE_PIC:
                Pic1ViewHolder holder1 = null;
                if (convertView == null){
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fp_1pic,null);
                    holder1  = new Pic1ViewHolder();
                    holder1.time = (TextView) convertView.findViewById(R.id.fp_1pic_time);
                    holder1.content = (TextView)convertView.findViewById(R.id.fp_1pic_content);
                    holder1.oldTime = (TextView)convertView.findViewById(R.id.fp_1pic_oldTime);
                    holder1.newTime = (TextView)convertView.findViewById(R.id.fp_1pic_newTime);
                    holder1.tag1 = (TextView)convertView.findViewById(R.id.fp_1pic_tag1);
                    holder1.tag2 = (TextView)convertView.findViewById(R.id.fp_1pic_tag2);
                    holder1.tag3 = (TextView)convertView.findViewById(R.id.fp_1pic_tag3);
                    holder1.iv = (ImageView)convertView.findViewById(R.id.fp_1pic_iv);
                    holder1.weather = (TextView)convertView.findViewById(R.id.fp_1pic_weather);
                    holder1.feelings = (TextView)convertView.findViewById(R.id.fp_1pic_feelings);
                    convertView.setTag(holder1);
                } else {
                    holder1 = (Pic1ViewHolder) convertView.getTag();
                }
                //-----------------
                //具体逻辑处理
                Log.d("执行到有图片的adapter","");
                if (itemBean.getIsMemo().equals("true")) {
                    holder1.tag1.setText("备忘");
                } else {
                    holder1.tag1.setText("日记");
                }
                if (itemBean.getTag2().equals("")){
                    holder1.tag2.setVisibility(View.GONE);
                } else {
                    holder1.tag2.setText(itemBean.getTag2());
                }
                if (itemBean.getTag3().equals("")){
                    holder1.tag3.setVisibility(View.GONE);
                } else {
                    holder1.tag3.setText(itemBean.getTag3());
                }

                holder1.content.setText(itemBean.getContent());
                holder1.oldTime.setText(itemBean.getOldTime());
                holder1.weather.setText(itemBean.getWeather());
                holder1.feelings.setText(itemBean.getFeelings());
//                Bitmap pic = GetImageUtils.getBMFromUri(getContext(),itemBean.getPicList().get(0));
//                holder1.iv.setImageBitmap(pic);
                Glide.with(getContext())
                        .load(itemBean.getPicList().get(0))
                        .asBitmap()
                        .into(holder1.iv);
                break;
            case ListItem.TYPE_TWO_PIC:
                Pic2ViewHolder holder2 = null;
                if (convertView == null){
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fp_2pic,null);
                    holder2  = new Pic2ViewHolder();
                    holder2.time = (TextView) convertView.findViewById(R.id.fp_2pic_time);
                    holder2.content = (TextView)convertView.findViewById(R.id.fp_2pic_content);
                    holder2.oldTime = (TextView)convertView.findViewById(R.id.fp_2pic_oldTime);
                    holder2.newTime = (TextView)convertView.findViewById(R.id.fp_2pic_newTime);
                    holder2.tag1 = (TextView)convertView.findViewById(R.id.fp_2pic_tag1);
                    holder2.tag2 = (TextView)convertView.findViewById(R.id.fp_2pic_tag2);
                    holder2.tag3 = (TextView)convertView.findViewById(R.id.fp_2pic_tag3);
                    holder2.iv1 = (ImageView)convertView.findViewById(R.id.fp_2pic_iv1);
                    holder2.iv2 = (ImageView)convertView.findViewById(R.id.fp_2pic_iv2);
                    holder2.weather = (TextView)convertView.findViewById(R.id.fp_2pic_weather);
                    holder2.feelings = (TextView)convertView.findViewById(R.id.fp_2pic_feelings);
                    convertView.setTag(holder2);
                } else {
                    holder2 = (Pic2ViewHolder) convertView.getTag();
                }
                //-----------------
                //具体逻辑处理
                if (itemBean.getIsMemo().equals("true")) {
                    holder2.tag1.setText("备忘");
                } else {
                    holder2.tag1.setText("日记");
                }
                if (itemBean.getTag2().equals("")){
                    holder2.tag2.setVisibility(View.GONE);
                } else {
                    holder2.tag2.setText(itemBean.getTag2());
                }
                if (itemBean.getTag3().equals("")){
                    holder2.tag3.setVisibility(View.GONE);
                } else {
                    holder2.tag3.setText(itemBean.getTag3());
                }

                holder2.content.setText(itemBean.getContent());
                holder2.oldTime.setText(itemBean.getOldTime());
                holder2.weather.setText(itemBean.getWeather());
                holder2.feelings.setText(itemBean.getFeelings());
                Bitmap pic1 = GetImageUtils.getBMFromUri(getContext(),itemBean.getPicList().get(0));
                holder2.iv1.setImageBitmap(pic1);
                Bitmap pic2 = GetImageUtils.getBMFromUri(getContext(),itemBean.getPicList().get(1));
                holder2.iv2.setImageBitmap(pic2);
                break;

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        //根据数据库中保存的图片uri数目来确定类型
        return getItem(position).getPicNum();
    }

    @Override
    public int getViewTypeCount() {
        return ListItem.TYPE_ALL_COUNT;
    }

    class Pic0ViewHolder{
        TextView time,content,oldTime,newTime,tag1,tag2,tag3,weather,feelings;
    }

    class Pic1ViewHolder{
        TextView time,content,oldTime,newTime,tag1,tag2,tag3,weather,feelings;
        ImageView iv;
    }

    class Pic2ViewHolder{
        TextView time,content,oldTime,newTime,tag1,tag2,tag3,weather,feelings;
        ImageView iv1,iv2;
    }
}
