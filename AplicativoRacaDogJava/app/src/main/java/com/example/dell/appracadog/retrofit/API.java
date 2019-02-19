package com.example.dell.appracadog.retrofit;

import com.example.dell.appracadog.home.model.CategoryResponse;
import com.example.dell.appracadog.login.model.request.SignUpRequest;
import com.example.dell.appracadog.login.model.response.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @POST("/signup")
    Observable<UserResponse> signUp(
            @Header("Content-Type") String contentType,
            @Body SignUpRequest request
    );

    @GET("/feed")
    Observable<CategoryResponse> getCategory(
            @Header("Authorization") String token,
            @Header("Content-Type") String contentType,
            @Query("category") String category
    );

    @GET("/feed")
    Observable<CategoryResponse> getCategory(
            @Header("Authorization") String token,
            @Header("Content-Type") String contentType
    );
}
