package com.example.myapplication.Services;

import com.example.myapplication.Domain.CityName;
import com.example.myapplication.Model.CreateReportRequest;
import com.example.myapplication.Model.GetReportsResponse;
import com.example.myapplication.Model.UserLogInRequest;
import com.example.myapplication.Model.UserLogInResponse;
import com.example.myapplication.Model.UserSignUpRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("api/Users/login")
    Call<UserLogInResponse> login(@Body UserLogInRequest userLogInRequest);
    @POST("api/Users/signup")
    Call<UserLogInResponse>signup(@Body UserSignUpRequest userSignUpRequest);
    @GET("api/Reports/city/{cityname}")
    Call<List<GetReportsResponse>>getReports(@Path("cityname") int cityName);
    @POST("api/Reports/reportProblem")
    Call<GetReportsResponse>reportProblem(@Header("Authorization") String token, @Body CreateReportRequest createReportRequest);

}
