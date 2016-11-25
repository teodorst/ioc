package com.example.ioc.evshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ioc.evshare.network.api.AuthService.AuthRequest;
import com.example.ioc.evshare.network.api.AuthService.AuthServiceManager;
import com.example.ioc.evshare.network.eventsBus.BusProvider;
import com.example.ioc.evshare.network.eventsBus.events.AuthEvent;
import com.squareup.otto.Subscribe;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // connect to bus event
//        BusProvider.bus().register(this);
        AuthServiceManager authService = AuthServiceManager.getInstance();

        // connect components
        loginButton = (Button) findViewById(R.id.login_button);
        emailEditText = (EditText) findViewById(R.id.login_activity_email_input);
        passwordEditText = (EditText) findViewById(R.id.login_activity_password_input);

        // set event listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            String userEmail;
            String userPassword;

            @Override
            public void onClick(View view) {
                userEmail = emailEditText.getText().toString();
                userPassword = emailEditText.getText().toString();

                if (validateUsernameAndPassword(userEmail, userPassword)) {
                    sendAuthRequest(userEmail, userPassword);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.bus().register(this);
    }

    @Override protected void onStop() {
        super.onStop();
        BusProvider.bus().unregister(this);
    }


    private void sendAuthRequest(String userEmail, String password) {
        AuthRequest requestBody = new AuthRequest();
        requestBody.setEmail("ana@mere.com");
        requestBody.setPassword("1234");
        BusProvider.bus().post(new AuthEvent.OnLoadingStart(requestBody));
    }

    @Subscribe public void onLoadingSuccessful(AuthEvent.OnLoadedSuccess onLoaded) {
        Log.d(TAG, "onLoadingSuccessful: " + onLoaded.getResponse());

        // i will switch activity
        switchToEventsActivity(onLoaded.getResponse());
    }

    @Subscribe
    public void onLoadingFailed(AuthEvent.OnLoadingError onLoadingFailed) {
        //try to fix or show a message
        Log.d(TAG, "onLoadingFailed: " + onLoadingFailed.getErrorMessage());
        // i will show an invalid email and password combination
    }

    private boolean validateUsernameAndPassword(String username, String password) {
        boolean isValid = true;
        if (username.isEmpty() || password.isEmpty()) {
            isValid = false;
        }

        if (username.length() > 100 || password.length() < 4 || password.length() > 40) {
            isValid = false;
        }

        if (!username.contains("@") || !username.contains(".")) {
            isValid = false;
        }

        return isValid;
    }

    private void switchToEventsActivity(String authToken){
        Intent intent = new Intent(this, EventsActivity.class);
        intent.putExtra("AUTH_TOKEN", authToken);
        startActivity(intent);
    }

}
