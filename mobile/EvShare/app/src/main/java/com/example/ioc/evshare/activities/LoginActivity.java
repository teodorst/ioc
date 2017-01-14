package com.example.ioc.evshare.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ioc.evshare.R;
import com.example.ioc.evshare.network.NetworkManager;
import com.example.ioc.evshare.network.api.AuthService.AuthRequest;
import com.example.ioc.evshare.network.api.AuthService.AuthServiceManager;
import com.example.ioc.evshare.network.api.UserService.UserServiceManager;
import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.AuthAction;
import com.example.ioc.evshare.network.actionsBus.actions.user.CreateUserAction;
import com.example.ioc.evshare.network.actionsBus.actions.user.GetUserAction;
import com.example.ioc.evshare.network.actionsBus.actions.user.ListUsersAction;
import com.squareup.otto.Subscribe;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private NetworkManager networkManager;
    private TextView createUserMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AuthServiceManager authService = AuthServiceManager.getInstance();
        UserServiceManager userService = UserServiceManager.getInstance();

        // connect components
        loginButton = (Button) findViewById(R.id.login_button);
        emailEditText = (EditText) findViewById(R.id.login_activity_email_input);
        passwordEditText = (EditText) findViewById(R.id.login_activity_password_input);
        createUserMessage = (TextView) findViewById(R.id.create_user_message);


        // connect to network manager
        networkManager = NetworkManager.getInstance();

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

        createUserMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToCreateUserActivity();
            }
        });

//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CreateUserActionMessage createUserEventMessage = new CreateUserActionMessage();
//                CreateUserRequest createUserRequest = new CreateUserRequest();
//                createUserRequest.setEmail("Teodorstefu@gmail.com");
//                createUserRequest.setPassword("ana-are-mere");
//                createUserRequest.setFirstName("Teodor");
//                createUserRequest.setLastName("Stefu");
//                createUserEventMessage.setRequestBody(createUserRequest);
//                BusProvider.bus().post(new CreateUserAction.OnLoadingStart(createUserEventMessage));
//                Log.d(TAG, "onClick: A plecat pe teava");
//            }
//        });
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                GetUserActionMessage getUserEventMessage = new GetUserActionMessage();
//                getUserEventMessage.setId("ana_are_mere");
//
//                BusProvider.bus().post(new GetUserAction.OnLoadingStart(getUserEventMessage));
//                Log.d(TAG, "onClick: A plecat pe teava get user");
//            }
//        });

//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ListUsersActionMessage listUserEventMessage = new ListUsersActionMessage();
//                listUserEventMessage.setPageNumber(0);
//                listUserEventMessage.setPageSize(100);
//                listUserEventMessage.setSortBy("FirstName");
//                listUserEventMessage.setSortOrder("ASC");
//                BusProvider.bus().post(new ListUsersAction.OnLoadingStart(listUserEventMessage));
//                Log.d(TAG, "onClick: A plecat pe teava list users");
//            }
//        });

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
        requestBody.setEmail("test@email.com");
        requestBody.setPassword("test");
        BusProvider.bus().post(new AuthAction.OnLoadingStart(requestBody));
    }

    @Subscribe public void onLoadingSuccessful(AuthAction.OnLoadedSuccess onLoaded) {
        Log.d(TAG, "onLoadingSuccessful: " + onLoaded.getResponse());

        // i will switch activity
        switchToEventsActivity(onLoaded.getResponse());
    }

    @Subscribe
    public void onLoadingFailed(AuthAction.OnLoadingError onLoadingFailed) {
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
        networkManager.setToken(authToken);
        Log.d(TAG, "switchToEventsActivity: Switching with token" + authToken);
        startActivity(intent);
    }

    private void switchToCreateUserActivity() {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
    }



//    // TEST SUBSCRIBERS
//    @Subscribe public void onLoadingSuccesful(CreateUserAction.OnLoadedSuccess response) {
//        Log.d(TAG, "onLoadingSuccessful: " + response.getResponse().getId());
//    }
//
//    @Subscribe public void onLoadingFailed(CreateUserAction.OnLoadingError response) {
//        Log.d(TAG, "onLoadingError: " + response.getErrorMessage());
//    }


    @Subscribe public void onLoadingFailed(GetUserAction.OnLoadingError response) {
        Log.d(TAG, "onLoadingFailed: " + response.getErrorMessage());
    }

    @Subscribe public void onLoadingSuccessful(GetUserAction.OnLoadingSuccessful response) {
        Log.d(TAG, "onLoadingSuccessful: " + response.getResponse().getId());
    }

    @Subscribe public void onLoadingFailed(ListUsersAction.OnLoadingError response) {
        Log.d(TAG, "onLoadingFailed: " + response.getErrorMessage());
    }

    @Subscribe public void onLoadingSuccessful(ListUsersAction.OnLoadingSuccessful response) {
        Log.d(TAG, "onLoadingSuccessful: " + response.getResponse().getTotalNumber());
    }



}
