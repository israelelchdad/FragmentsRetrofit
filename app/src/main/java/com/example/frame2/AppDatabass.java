package com.example.frame2;

import android.app.Activity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Result.class,ResultsItem.class},  version = 3)
public abstract class AppDatabass extends RoomDatabase {
    public static final String DATABASE_NAME = "movies";
    public static AppDatabass INSTANC;
    public abstract MovieDeo movieDeo();
    public abstract TrailerDeo trailerDeo();

    public static AppDatabass getinstansce(Activity context){
        if (INSTANC == null){
            INSTANC = Room.databaseBuilder(context.getApplicationContext(),AppDatabass.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }
        return INSTANC;
    }
}
