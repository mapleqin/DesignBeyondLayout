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
package com.toaker.designbeyondlayout.helper;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.github.ksoichiro.android.observablescrollview.TouchInterceptionFrameLayout;
import com.nineoldandroids.view.ViewHelper;
import com.toaker.designbeyondlayout.DesignBeyondLayout;

/**
 * Decorator for DesignBeyondLayout
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Time Create by 2015/5/13 20:27
 */
public class DesignBeyondLayoutHelper implements TouchInterceptionFrameLayout.TouchInterceptionListener {


    protected DesignBeyondLayout mDesignBeyondLayout;

    protected DesignBeyondHeaderView mDesignBeyondHeader;

    protected View               mContentView;

    private int mSlop;

    private boolean mScrolled;

    public DesignBeyondLayoutHelper(DesignBeyondLayout layout){
        this.mDesignBeyondLayout = layout;
        this.mDesignBeyondHeader = mDesignBeyondLayout.getDesignBeyondHeader();
        this.mContentView = mDesignBeyondLayout.getContentView();
        this.mDesignBeyondLayout.setScrollInterceptionListener(this);
        ViewConfiguration vc = ViewConfiguration.get(mDesignBeyondLayout.getContext());
        mSlop = vc.getScaledTouchSlop();
    }

    public void onDetachedFromWindow() {
    }


    @Override
    public boolean shouldInterceptTouchEvent(MotionEvent event, boolean b, float diffX, float diffY) {
        if (!mScrolled && mSlop < Math.abs(diffX) && Math.abs(diffY) < Math.abs(diffX)) {
            // Horizontal scroll is maybe handled by ViewPager
            return false;
        }

//        Scrollable scrollable = mDesignBeyondLayout.getCurrentScrollable();
//        if (scrollable == null) {
//            mScrolled = false;
//            return false;
//        }

        // If interceptionLayout can move, it should intercept.
        // And once it begins to move, horizontal scroll shouldn't work any longer.
        int flexibleSpace = mDesignBeyondHeader.getHeaderViewHeight();
        int translationY = (int) ViewHelper.getTranslationY(mDesignBeyondLayout);
        boolean scrollingUp = 0 < diffY;
        boolean scrollingDown = diffY < 0;
        if (scrollingUp) {
            if (translationY < 0) {
                mScrolled = true;
                return true;
            }
        } else if (scrollingDown) {
            if (-flexibleSpace < translationY) {
                mScrolled = true;
                return true;
            }
        }
        mScrolled = false;
        return false;
    }

    @Override
    public void onDownMotionEvent(MotionEvent ev) {
    }

    @Override
    public void onMoveMotionEvent(MotionEvent ev, float diffX, float diffY) {
        int flexibleSpace = mDesignBeyondHeader.getHeaderViewHeight();
        float translationY = ScrollUtils.getFloat(ViewHelper.getTranslationY(mDesignBeyondLayout) + diffY, -flexibleSpace, 0);
        updateFlexibleSpace(translationY);
    }

    @Override
    public void onUpOrCancelMotionEvent(MotionEvent ev) {
        mScrolled = false;
    }

    private void updateFlexibleSpace(float translationY) {
        ViewHelper.setTranslationY(mDesignBeyondLayout, translationY);
    }
}
