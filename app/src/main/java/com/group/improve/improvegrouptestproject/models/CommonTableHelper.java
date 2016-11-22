package com.group.improve.improvegrouptestproject.models;


import com.group.improve.improvegrouptestproject.helpers.ImproveGroupApplication;

abstract class CommonTableHelper   {
    /**
    * Table name
    */
    String TABLE_NAME = "";
    DataBaseHelper dbhelper;

    public CommonTableHelper(){
        this.dbhelper=
                ImproveGroupApplication.getDbManager().getDbhelper();
    }

}
