package com.group.improve.improvegrouptestproject.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 *
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase myDataBase;
    public  SQLiteDatabase getMyDataBase(){
        return    this.myDataBase;
    }


    private Context mCurrentContext;

    private static final String DB_NAME= "improve_group.sqlite";
    private static final String LOG_NAME= "DataBaseHelper";

    public DataBaseHelper(Context context){
        super(context,DB_NAME, null, DATABASE_VERSION);

        this.mCurrentContext = context;
    }



    public void dbSystemStart(){
        boolean dbExist = checkDataBase();

        if(!dbExist){
            this.getReadableDatabase();

            try{
                this.copyDataBase();
                this.checkDataBase();
            }
            catch (IOException e){
                    Log.e("DataBaseHelper", "Error copying database");

                throw new Error("Error copying database");
            }
        }

    }


    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try{
            String myPath = mCurrentContext.getDatabasePath(DB_NAME).getPath();
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        }
        catch(SQLiteException e){
                Log.v(LOG_NAME, "There are not db");
        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null;
    }



    private void copyDataBase() throws IOException {

        InputStream myInput = mCurrentContext.getAssets().open(DB_NAME);
        String outFileName = mCurrentContext.getDatabasePath(DB_NAME).getPath();

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() {

        try {
            String myPath =  mCurrentContext.getDatabasePath(DB_NAME).getPath();
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        }
        catch(SQLiteException e){
                Log.v(LOG_NAME, "DB doen't be opened");
        }
    }

    @Override
    public synchronized void close()
    {
        if(myDataBase != null)
            myDataBase.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
