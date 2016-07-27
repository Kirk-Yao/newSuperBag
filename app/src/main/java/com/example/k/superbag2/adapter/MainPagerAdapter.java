package com.example.k.superbag2.adapter;


import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by K on 2016/7/25.
 */
public class MainPagerAdapter extends android.support.v4.view.PagerAdapter {

    private List<View> viewList;

    public MainPagerAdapter(List<View> list){
        viewList = list;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = viewList.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
