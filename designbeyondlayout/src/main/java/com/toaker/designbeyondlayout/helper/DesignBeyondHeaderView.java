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

import android.view.View;

import com.toaker.designbeyondlayout.DesignBeyondLayout;


/**
 * Decorator for DesignBeyondLayout
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Time Create by 2015/5/13 19:10
 */
public class DesignBeyondHeaderView {

    protected DesignBeyondLayout mDesignBeyondLayout;

    protected View                  mHeaderView;

    public DesignBeyondHeaderView(DesignBeyondLayout layout){
        this.mDesignBeyondLayout = layout;
        this.mHeaderView = mDesignBeyondLayout.getHeaderView();
    }

    public int getHeaderViewHeight(){
        if(mHeaderView != null){
            return mHeaderView.getHeight();
        }
        return 0;
    }

    public View getHeaderView(){
        return mHeaderView;
    }
}
