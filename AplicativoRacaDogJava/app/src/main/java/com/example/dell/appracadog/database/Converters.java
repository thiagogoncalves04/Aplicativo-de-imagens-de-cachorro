package com.example.dell.appracadog.database;

import android.arch.persistence.room.TypeConverter;

import com.example.dell.appracadog.login.model.request.SignUpRequest;
import com.example.dell.appracadog.login.model.response.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class Converters {
    @TypeConverter
    public Date toDate(Long timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return new Date(timestamp);
        }
    }

    @TypeConverter
    public Long toTimestamp(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromList(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public List<Long> fromLong(String value) {
        Type listType = new TypeToken<List<Long>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromLong(List<Long> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public User fromUser(String value) {
        Type listType = new TypeToken<User>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromUser(User user) {
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    @TypeConverter
    public SignUpRequest fromEmail(String value) {
        Type listType = new TypeToken<SignUpRequest>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromEmail(SignUpRequest request) {
        Gson gson = new Gson();
        return gson.toJson(request);
    }
}
