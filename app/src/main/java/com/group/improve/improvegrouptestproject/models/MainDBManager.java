package com.group.improve.improvegrouptestproject.models;

import android.content.Context;

public class MainDBManager{

    private DataBaseHelper dbhelper;

    public MainDBManager(Context context){

        this.dbhelper=new DataBaseHelper(context);
        this.dbPreparation();
    }


    private void dbPreparation(){
        //if db doesn't exist , create it
        this.dbhelper.dbSystemStart();
        //try open
        this.dbhelper.openDataBase();
    }

    public DataBaseHelper getDbhelper(){
        return  dbhelper;
    }
}


