package com.example.rakeshdemotestretrofit.activity.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

// LoginActivity.java
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.rakeshdemotestretrofit.R;
import com.example.rakeshdemotestretrofit.db.SharedPreferencesHelper;
import com.example.rakeshdemotestretrofit.activity.transaction.TransactionActivity;

public class LoginActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(SharedPreferencesHelper.getToken(this)!=null){
            Intent intent = new Intent(LoginActivity.this, TransactionActivity.class);
            startActivity(intent);
            finish();
        }
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login_button);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // Observe login response
        authViewModel.getLoginResponse().observe(this, loginResponse -> {
            // Save token and move to next activity
            String token = loginResponse.getToken();
            startActivity(new Intent(LoginActivity.this, TransactionActivity.class));
            finish();
        });

        // Observe error message
        authViewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
        });

        // Handle login button click
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            authViewModel.login(this,username, password);
        });
    }
}
