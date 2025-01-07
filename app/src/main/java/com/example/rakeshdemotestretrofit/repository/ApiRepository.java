package com.example.rakeshdemotestretrofit.repository;


import android.content.Context;
import androidx.annotation.NonNull;

import com.example.rakeshdemotestretrofit.api.ApiService;
import com.example.rakeshdemotestretrofit.model.LoginRequest;
import com.example.rakeshdemotestretrofit.model.LoginResponse;
import com.example.rakeshdemotestretrofit.api.RetrofitClient;
import com.example.rakeshdemotestretrofit.db.SharedPreferencesHelper;
import com.example.rakeshdemotestretrofit.model.Transaction;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiRepository {
    private final ApiService apiService;

    public ApiRepository() {
        apiService = RetrofitClient.getClientWithoutToken().create(ApiService.class);
    }

    // Login API Call (no token required)
    public void login(String username, String password, final ApiCallback<LoginResponse> callback) {
        LoginRequest request = new LoginRequest(username, password);
        apiService.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Login failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    // Fetch Transactions API Call (with token)
    public void getTransactions(Context context,final ApiCallback<List<Transaction>> callback) {
        // Retrieve the token from EncryptedSharedPreferences
        String token = SharedPreferencesHelper.getToken(context); // Make sure to pass context
        if (token == null) {
            callback.onFailure("Token is missing");
            return;
        }

        // Create a Retrofit client with token
        Retrofit retrofit = RetrofitClient.getClientWithToken(token);
        ApiService serviceWithToken = retrofit.create(ApiService.class);

        // Make the API call to get transactions
        serviceWithToken.getTransactions().enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(@NonNull Call<List<Transaction>> call, @NonNull Response<List<Transaction>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch transactions");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Transaction>> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T data);
        void onFailure(String error);
    }
}

