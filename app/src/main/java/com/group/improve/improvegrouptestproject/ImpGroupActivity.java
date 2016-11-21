package com.group.improve.improvegrouptestproject;

import android.app.Activity;
import android.os.Bundle;

import com.group.improve.improvegrouptestproject.presenters.IPresenter;

/**
 * Parent for all our activity
 */
public class ImpGroupActivity extends Activity {

    protected IPresenter mPresenter;
    public IPresenter getmPresenter(){
        return this.mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
