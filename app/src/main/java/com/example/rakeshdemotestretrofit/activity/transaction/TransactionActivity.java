package com.example.rakeshdemotestretrofit.activity.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.rakeshdemotestretrofit.helper.BiometricHelper;
import com.example.rakeshdemotestretrofit.R;
import com.example.rakeshdemotestretrofit.db.SharedPreferencesHelper;
import com.example.rakeshdemotestretrofit.activity.login.LoginActivity;
import com.example.rakeshdemotestretrofit.adapter.TransactionAdapter;

import java.util.concurrent.Executor;

public class TransactionActivity extends AppCompatActivity {

    private TransactionViewModel transactionViewModel;
    private ListView transactionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        transactionListView = findViewById(R.id.transactions_list_view);

        // Initialize ViewModel
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        // Check if Biometric Authentication is available
        if (BiometricHelper.isBiometricSupported(this)) {
            initiateBiometricAuthentication();
        } else {
            // Show a message if biometric is not supported
            Toast.makeText(this, "Biometric authentication not supported", Toast.LENGTH_SHORT).show();
        }
        ImageView logoutIcon = findViewById(R.id.logout_icon);
        logoutIcon.setOnClickListener(v -> logout());

    }

    // Start the biometric authentication process
    private void initiateBiometricAuthentication() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // On success, call the method to fetch transactions
                fetchTransactions();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(TransactionActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(TransactionActivity.this, "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }
        });

        // Set up the biometric prompt dialog
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Please authenticate to access transactions")
                .setNegativeButtonText("Cancel")
                .build();

        // Show the biometric prompt
        biometricPrompt.authenticate(promptInfo);
    }

    // Fetch transactions after biometric authentication is successful
    private void fetchTransactions() {
        // Fetch transactions from the ViewModel
        transactionViewModel.fetchTransactions(this);

        // Observe the transaction list
        transactionViewModel.getTransactionList().observe(this, transactions -> {
            // Populate the ListView with transactions
            TransactionAdapter adapter = new TransactionAdapter(this, transactions);
            transactionListView.setAdapter(adapter);
        });

        // Observe error message
        transactionViewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(TransactionActivity.this, error, Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Check if Biometric Authentication is available
        if (BiometricHelper.isBiometricSupported(this)) {
            initiateBiometricAuthentication();
        } else {
            // Show a message if biometric is not supported
            Toast.makeText(this, "Biometric authentication not supported", Toast.LENGTH_SHORT).show();
        }
    }


    private void logout() {
        // Clear token from SharedPreferences
        SharedPreferencesHelper.clearToken(this);

        // Reset ViewModel state
        transactionViewModel.clearToken(this);

        // Redirect to login screen
        Intent intent = new Intent(TransactionActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
