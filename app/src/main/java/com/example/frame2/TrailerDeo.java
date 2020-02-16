package com.example.frame2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface TrailerDeo {
    @Query("SELECT * FROM  ResultsItem  WHERE  movieid = :movieid")
    ResultsItem getTrelair(int  movieid);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Void insert(ResultsItem mresultsItem);

}
