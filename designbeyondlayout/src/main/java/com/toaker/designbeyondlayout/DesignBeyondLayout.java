/*******************************************************************************
 * Copyright 2015-2019 Toaker DesignBeyondLayout
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.toaker.designbeyondlayout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.github.ksoichiro.android.observablescrollview.Scrollable;
import com.github.ksoichiro.android.observablescrollview.TouchInterceptionFrameLayout;
import com.toaker.common.tlog.TLog;
import com.toaker.designbeyondlayout.helper.DesignBeyondHeaderView;
import com.toaker.designbeyondlayout.helper.DesignBeyondLayoutHelper;

/**
 * Decorator for DesignBeyondLayout
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Time Create by 2015/5/13 17:07
 */
public class DesignBeyondLayout extends TouchInterceptionFrameLayout{

    private static final boolean DEBUG = true;

    private static final String LOG_TAG = "DesignBeyondLayout:";

    protected View mHeaderView;

    protected View mContentView;

    protected DesignBeyondHeaderView mDesignBeyondHeader;

    protected DesignBeyondLayoutHelper mDesignBeyondHelper;

    public DesignBeyondLayout(Context context) {
        super(context);
    }

    public DesignBeyondLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DesignBeyondLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DesignBeyondLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        if(getChildCount() > 2){
            throw new IllegalStateException("DesignBeyondViewPager must contain two sub-control!");
        }else if(getChildCount() == 2){
            mHeaderView = getChildAt(0);
            mContentView = getChildAt(1);
        }else if(getChildCount() == 1){
            mContentView = getChildAt(1);
        }
        mDesignBeyondHeader = new DesignBeyondHeaderView(this);
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    public Scrollable getCurrentScrollable(){
        return null;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void initialized(){
        mDesignBeyondHelper = new DesignBeyondLayoutHelper(this);
        ScrollUtils.addOnGlobalLayoutListener(this,new Runnable() {
            @Override
            public void run() {
                mContentView.setPadding(0, mDesignBeyondHeader.getHeaderViewHeight(), 0, 0);
                FrameLayout.LayoutParams params = (LayoutParams) getLayoutParams();
                params.height = getScreenHeight() +  mDesignBeyondHeader.getHeaderViewHeight();
                if(DEBUG){
                    TLog.d(LOG_TAG,"height:%s",params.height);
                }
                requestLayout();
            }
        });

    }

    public int getScreenHeight(){
        return ((Activity)getContext()).findViewById(android.R.id.content).getHeight();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mDesignBeyondHelper != null){
            mDesignBeyondHelper.onDetachedFromWindow();
        }
    }

    public View getHeaderView(){
        return mHeaderView;
    }

    public View getContentView(){
        return mContentView;
    }

    public DesignBeyondHeaderView getDesignBeyondHeader(){
        return mDesignBeyondHeader;
    }

}
