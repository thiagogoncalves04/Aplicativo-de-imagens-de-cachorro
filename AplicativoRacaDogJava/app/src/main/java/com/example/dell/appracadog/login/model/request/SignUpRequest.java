package com.example.dell.appracadog.login.model.request;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Emails")
public class SignUpRequest {

    public SignUpRequest(@NonNull String email) {
        this.email = email;
    }

    @Expose
    @PrimaryKey
    @NonNull
    @SerializedName("email")
    private String email;

    public @NonNull String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

}
