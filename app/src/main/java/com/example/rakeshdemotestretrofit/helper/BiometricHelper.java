package com.example.rakeshdemotestretrofit.helper;


import androidx.biometric.BiometricManager;
import android.content.Context;
import android.os.Build;


public class BiometricHelper {

    public static boolean isBiometricSupported(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            BiometricManager biometricManager = BiometricManager.from(context);

            // Check the biometric availability
            int canAuthenticate = biometricManager.canAuthenticate();
            if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
                return true;  // Biometric is available and set up
            } else {
                return false;  // Biometric is either not available or not set up
            }
        } else {
            // For devices running below Android Pie (API 28), biometric authentication is not supported
            return false;
        }
    }
}

