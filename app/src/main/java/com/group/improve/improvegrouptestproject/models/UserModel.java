package com.group.improve.improvegrouptestproject.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.group.improve.improvegrouptestproject.models.DTO.User;

import java.util.ArrayList;

/**
 *
 */
public class UserModel extends CommonTableHelper  {

    private static final String KEY_ROW_ID = "id";
    private static final String KEY_ROW_FULL_NAME = "fullname";
    private static final String KEY_ROW_USER_NAME = "username";
    private static final String KEY_ROW_HASH    = "hash";
    private static final String KEY_ROW_BIRTH   = "birth";
    private static final String KEY_ROW_EMAIL   = "email";

    public UserModel(DataBaseHelper dbhelper_){
        super();
        dbhelper       =dbhelper_;
        this.TABLE_NAME="users";
    }


    public User getUser(String userName){

        try{
            Cursor cursor = dbhelper.getMyDataBase()
                    .rawQuery( "SELECT * FROM " +TABLE_NAME+" WHERE "+KEY_ROW_USER_NAME+"="+userName, null);

            if (cursor != null) {
                cursor.moveToFirst();


                return new User.Builder()
                        .setbId(cursor.getInt(cursor.getColumnIndex(KEY_ROW_ID)))
                        .setbFullName(cursor.getString(cursor.getColumnIndex(KEY_ROW_FULL_NAME)))
                        .setbUserName(cursor.getString(cursor.getColumnIndex(KEY_ROW_USER_NAME)))
                        .setbHash(cursor.getString(cursor.getColumnIndex(KEY_ROW_HASH)))
                        .setbEmail(cursor.getString(cursor.getColumnIndex(KEY_ROW_BIRTH)))
                        .setbBirth(cursor.getInt(cursor.getColumnIndex(KEY_ROW_EMAIL)))
                        .build();
            }

            cursor.close();
        }
        catch (SQLiteException e){
            Log.e("Ex", "Error obtaining sql data!");
        }


        return null;
    }



    public int saveUser(User data){
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_ROW_FULL_NAME,         data.getmFullName());
        initialValues.put(KEY_ROW_USER_NAME,      data.getmUserName());
        initialValues.put(KEY_ROW_HASH,    data.getmHash());
        initialValues.put(KEY_ROW_BIRTH,    data.getmBirth());
        initialValues.put(KEY_ROW_EMAIL,  data.getmEmail());

        return (int)dbhelper.getMyDataBase().insert(TABLE_NAME, null, initialValues);
    }




}
