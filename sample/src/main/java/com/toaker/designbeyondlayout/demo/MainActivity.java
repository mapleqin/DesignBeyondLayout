package com.toaker.designbeyondlayout.demo;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.toaker.designbeyondlayout.DesignBeyondLayout;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    LayoutInflater inflater;

    ObservableListView mListView;

    Random mRandom = new Random();

    DesignBeyondLayout mDesignBeyondLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ObservableListView) findViewById(R.id.listview);
        mDesignBeyondLayout = (DesignBeyondLayout) findViewById(R.id.design_beyond_layout);
        mDesignBeyondLayout.initialized();
        inflater = LayoutInflater.from(this);
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 50;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if(convertView == null){
                    convertView = inflater.inflate(R.layout.listview_item,null);
                    holder = new ViewHolder();
                    holder.mContentView = convertView.findViewById(R.id.list_item_view);
                    convertView.setTag(holder);
                }else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.mContentView.setBackgroundColor(getRandomColor());
                return convertView;
            }

            class ViewHolder{
                public View mContentView;
            }
        });
    }

    private int getRandomColor(){
        int r = mRandom.nextInt(255);
        int g = mRandom.nextInt(255);
        int b = mRandom.nextInt(255);
        return Color.rgb(r,g,b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
