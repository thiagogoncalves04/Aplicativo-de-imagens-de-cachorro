package com.example.dell.appracadog.login.model.response;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

@Entity(tableName = "Users")
public class UserResponse {

    @Expose
    private User user;

    @Expose
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    public User getUser() {
        return user;
    }

    public static class Builder {
        private User user;

        public UserResponse.Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public UserResponse build() {
            UserResponse userResponse = new UserResponse();
            userResponse.user = user;
            return userResponse;
        }

    }

    public void setUser(User user) {
        this.user = user;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

}
