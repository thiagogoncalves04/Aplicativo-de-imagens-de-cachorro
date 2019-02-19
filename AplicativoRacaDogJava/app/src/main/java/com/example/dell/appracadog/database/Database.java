package com.example.dell.appracadog.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.dell.appracadog.adapter.CategoryDAO;
import com.example.dell.appracadog.adapter.UserDAO;
import com.example.dell.appracadog.home.model.CategoryResponse;
import com.example.dell.appracadog.login.model.request.SignUpRequest;
import com.example.dell.appracadog.login.model.response.UserResponse;

@android.arch.persistence.room.Database(entities = {SignUpRequest.class, UserResponse.class, CategoryResponse.class}, version = 6, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class Database extends RoomDatabase {

    public abstract UserDAO userDAO();
    public abstract CategoryDAO categoryDAO();

    private static volatile Database INSTANCE;

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "iddog_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

