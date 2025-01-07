package com.example.rakeshdemotestretrofit.activity.transaction;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rakeshdemotestretrofit.repository.ApiRepository;
import com.example.rakeshdemotestretrofit.db.SharedPreferencesHelper;
import com.example.rakeshdemotestretrofit.model.Transaction;

import java.util.List;

public class TransactionViewModel extends ViewModel {

    private final MutableLiveData<List<Transaction>> transactionList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final ApiRepository apiRepository;
    private final MutableLiveData<String> tokenLiveData = new MutableLiveData<>();
    public TransactionViewModel() {
        apiRepository = new ApiRepository();
    }

    // Fetch transactions method
    public void fetchTransactions(Context context) {
        apiRepository.getTransactions(context,new ApiRepository.ApiCallback<List<Transaction>>() {
            @Override
            public void onSuccess(List<Transaction> data) {
                transactionList.setValue(data);
            }

            @Override
            public void onFailure(String error) {
                errorMessage.setValue(error);
            }
        });
    }

    public LiveData<List<Transaction>> getTransactionList() {
        return transactionList;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    // Call this method from the Activity to clear the token in ViewModel
    public void clearToken(Context context) {
        // Clear token from SharedPreferences
        SharedPreferencesHelper.clearToken(context);

        // Notify observers (if needed, for updating UI)
        tokenLiveData.setValue(null); // or any appropriate response
    }

    // Get the live data of token if needed
    public LiveData<String> getToken() {
        return tokenLiveData;
    }
}

