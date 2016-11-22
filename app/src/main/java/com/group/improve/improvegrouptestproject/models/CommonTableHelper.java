package com.group.improve.improvegrouptestproject.models;

import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 *  Класс от которого наследуются все мэнеджеры таблиц
 *
 */
 abstract class CommonTableHelper   {

    /**
     * Table name
     */
     String TABLE_NAME = "";

    /**
    * Мэнеджер базы данных
    */
    DataBaseHelper dbhelper;




}
