package com.example.festival;
import android.app.Application;
import android.content.Context;

import com.example.festival.dao.DatabaseHelper;
public class MyFestival extends Festival{
    private static DatabaseHelper dbHelper;
    @Override
    public void onCreate(){
        super.onCreate();
        // Initialisation bdd
        dbHelper = new DatabaseHelper(getFestivalContext());
        dbHelper.getWritableDatabase();
    }

    private Context getFestivalContext() {
    }

    public static DatabaseHelper getDbHelper(){
        return dbHelper;
    }
    public static void setDbHelper(DatabaseHelper dbHelper){
        Festival.dbHelper = dbHelper;
    }
}
