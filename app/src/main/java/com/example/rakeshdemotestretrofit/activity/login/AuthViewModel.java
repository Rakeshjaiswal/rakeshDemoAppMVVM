package com.example.rakeshdemotestretrofit.activity.login;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rakeshdemotestretrofit.repository.ApiRepository;
import com.example.rakeshdemotestretrofit.model.LoginResponse;
import com.example.rakeshdemotestretrofit.db.SharedPreferencesHelper;

public class AuthViewModel extends ViewModel {

    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final ApiRepository apiRepository;

    public AuthViewModel() {
        apiRepository = new ApiRepository();
    }

    // Login method
    public void login(Context context,String username, String password) {
        apiRepository.login(username, password, new ApiRepository.ApiCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse data) {
                SharedPreferencesHelper.saveToken(context, data.getToken());
                loginResponse.setValue(data);
            }

            @Override
            public void onFailure(String error) {
                errorMessage.setValue(error);
            }
        });
    }

    public LiveData<LoginResponse> getLoginResponse() {
        return loginResponse;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}


