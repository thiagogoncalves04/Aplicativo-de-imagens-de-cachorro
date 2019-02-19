package com.example.dell.appracadog.login.model.response;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    @SerializedName("__v")
    private Long _v;
    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("createdAt")
    private String createdAt;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("token")
    private String token;
    @Expose
    @SerializedName("updatedAt")
    private String updatedAt;
    @Expose
    @SerializedName("user")
    private User user;

    @Expose
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public Long get_v() {
        return _v;
    }

    public String get_id() {
        return _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }

    public static class Builder {

        private Long _V;
        private String _id;
        private String createdAt;
        private String email;
        private String token;
        private String updatedAt;
        private User user;

        public User.Builder with_V(Long _V) {
            this._V = _V;
            return this;
        }

        public User.Builder with_id(String _id) {
            this._id = _id;
            return this;
        }

        public User.Builder withCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public User.Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public User.Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public User.Builder withUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User.Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public User build() {
            User user = new User();
            user._v = _V;
            user._id = _id;
            user.createdAt = createdAt;
            user.email = email;
            user.token = token;
            user.updatedAt = updatedAt;
            user.user = user;
            return user;
        }
    }

    public void set_v(Long _v) {
        this._v = _v;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }
}