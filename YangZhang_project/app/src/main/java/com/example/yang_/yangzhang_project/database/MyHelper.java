package com.example.yang_.yangzhang_project.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.yang_.yangzhang_project.Constants;

public class MyHelper extends SQLiteOpenHelper {

    // name of the database
    public static final String DB_FILE_NAME = "recipe.db";
    public static final  int DB_VERSION = 1; //first database structure


    // (context, name, factory,version), dont need factory argument
    public MyHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //pass reference to the managed database file,create the table
        //if have multiple table call each table in turn
        db.execSQL(ItemsTable.SQL_CREATE);
    }

    //update database and increment database version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //wipe out the database and start over
        db.execSQL(ItemsTable.SQL_DELETE); // drops table from database
        onCreate(db);

    }
}



