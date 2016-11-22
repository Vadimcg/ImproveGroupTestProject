package com.group.improve.improvegrouptestproject.helpers;

import android.app.Application;

import com.group.improve.improvegrouptestproject.models.MainDBManager;



public class ImproveGroupApplication extends Application{

    private static ImproveGroupApplication application;

    private static MainDBManager mDBManager;

    @Override
    public void onCreate(){
        super.onCreate();

        application=this;
        mDBManager=new MainDBManager(this.getApplicationContext());
    }


    public static ImproveGroupApplication getApplication(){
        return application;
    }

    public  static MainDBManager getDbManager(){
        return   mDBManager;
    }


}
