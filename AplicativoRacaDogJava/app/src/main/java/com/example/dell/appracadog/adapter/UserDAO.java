package com.example.dell.appracadog.adapter;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dell.appracadog.login.model.response.UserResponse;

import io.reactivex.Flowable;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserResponse user);

    @Update
    void update(UserResponse user);

    @Delete
    void delete(UserResponse user);

    @Query("Select * from Users limit 5")
    Flowable<UserResponse> getAll();

    @Query("Select * from Users where id = :id")
    UserResponse getById(long id);
}
