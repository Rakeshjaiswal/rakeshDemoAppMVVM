This Android application demonstrates the MVVM (Model-View-ViewModel) architecture with the following features:

API Integration for login and transaction management.
Biometric Authentication for secure access on subsequent launches.
Secure Token Storage using EncryptedSharedPreferences to store authentication tokens.
Logout functionality that clears the authentication token.

Features
1. MVVM Architecture
The app uses the MVVM (Model-View-ViewModel) design pattern to separate concerns:

Model: Represents the data layer. It handles network requests (API calls) and data manipulation.
View: The UI layer (e.g., Activity or Fragment) that observes the data from ViewModel and displays it to the user.
ViewModel: The intermediary between the View and the Model. It holds the UI-related data and business logic.
2. API Integration
Login API: Authenticating the user with a POST request to https://api.prepstripe.com/login using a username and password.
Transaction API: Fetching a list of transactions with a GET request to https://api.prepstripe.com/transactions.
3. Biometric Authentication
Implements Biometric Authentication to secure user login on subsequent app launches.
Uses Android's BiometricPrompt to verify user identity through fingerprint, face recognition, etc.
4. Secure Token Handling
EncryptedSharedPreferences is used to securely store the authentication token received after successful login.
The token is stored in a secured way, ensuring that the token is encrypted and protected from unauthorized access.
5. Logout Functionality
When the user logs out, the stored token is cleared from EncryptedSharedPreferences, and the user is redirected to the login screen.
