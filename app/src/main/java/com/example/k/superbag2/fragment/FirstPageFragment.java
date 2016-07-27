package com.example.k.superbag2.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.k.superbag2.R;
import com.example.k.superbag2.activity.EditActivity;
import com.example.k.superbag2.adapter.FirstpageAdapter;
import com.example.k.superbag2.bean.ItemBean;
import com.example.k.superbag2.others.ListItem;
import com.example.k.superbag2.utils.GetImageUtils;
import com.example.k.superbag2.utils.SuperbagDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2016/6/26.
 */
public class FirstPageFragment extends Fragment {

    private ImageView fPBackgroundIV,fPHeadIconIV;
    private TextView fPSummaryTV;
    private ListView fPListView;

    public static List<ListItem> list = new ArrayList<>();
    private Context context;
    private int takeFlags;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_firstpage,container,false);

        Log.d("执行到了","first的onCreate()");
        context = getContext();
        fPBackgroundIV = (ImageView)v.findViewById(R.id.top_background);
        fPHeadIconIV = (ImageView)v.findViewById(R.id.top_head_icon);
        fPSummaryTV = (TextView)v.findViewById(R.id.top_summary);
        fPListView = (ListView)v.findViewById(R.id.firstPage_lv);

        initView();
        return v;
    }

    //将ListView的设置写在onStart()中，则可以动态刷新adapter
    @Override
    public void onStart() {
        initView();
        initListView();
        super.onStart();
    }

    //根据本地存储的数据，初始化背景，头像
    private void initView(){


    }

    private void initListView(){
        SuperbagDatabaseHelper helper = new SuperbagDatabaseHelper(context,"superbag.db",null,1);
        final List<ItemBean> itemBeanList = helper.queryBD();
        Log.d("列表长度：",itemBeanList.size()+"");
        FirstpageAdapter adapter = new FirstpageAdapter(context,R.layout.item_fp_1pic,itemBeanList);
        fPListView.setAdapter(adapter);
        //----------------
        setListViewHeightBasedOnChildren(fPListView);
        adapter.notifyDataSetChanged();

        fPListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //得到数据库中的行号
                int index = itemBeanList.size() - i - 1;
                Log.d("索引是",index+"");
                Intent intent = new Intent(context,EditActivity.class);
                intent.putExtra("lineIndex",index);
                startActivity(intent);
            }
        });

        fPListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });

        fPListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * 为了解决ListView在ScrollView中只能显示一行数据的问题
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
