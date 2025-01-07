package com.example.rakeshdemotestretrofit.api;

import com.example.rakeshdemotestretrofit.model.LoginRequest;
import com.example.rakeshdemotestretrofit.model.LoginResponse;
import com.example.rakeshdemotestretrofit.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    // Login POST request
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    // Transactions GET request
    @GET("transactions")
    Call<List<Transaction>> getTransactions();
}

