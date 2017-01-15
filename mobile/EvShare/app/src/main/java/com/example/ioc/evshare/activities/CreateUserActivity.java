package com.example.ioc.evshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ioc.evshare.R;
import com.example.ioc.evshare.network.actionsBus.BusProvider;
import com.example.ioc.evshare.network.actionsBus.actions.user.CreateUserAction;
import com.example.ioc.evshare.network.actionsBus.actions.user.message.CreateUserActionMessage;
import com.example.ioc.evshare.network.api.UserService.CreateUserRequest;
import com.example.ioc.evshare.network.api.UserService.UserServiceManager;
import com.squareup.otto.Subscribe;

public class CreateUserActivity extends AppCompatActivity {

    private static final String TAG = "CreateUserActivity";
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private Button createButton;
    private TextView passwordErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // connect UI
        firstNameEditText = (EditText) findViewById(R.id.create_user_firstname_input);
        lastNameEditText = (EditText) findViewById(R.id.create_user_lastname_input);
        emailEditText = (EditText) findViewById(R.id.create_user_email_input);
        passwordEditText = (EditText) findViewById(R.id.create_user_password_input);
        repeatPasswordEditText = (EditText) findViewById(R.id.create_user_password_repeat_input);
        createButton = (Button) findViewById(R.id.create_user_button);
        passwordErrorMessage = (TextView) findViewById(R.id.password_error);

        UserServiceManager userServiceManager = UserServiceManager.getInstance();

        // buttons handlers
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + passwordEditText.getText());
                Log.d(TAG, "onClick: " + repeatPasswordEditText.getText());

                if (passwordEditText.getText().toString().equals(repeatPasswordEditText.getText().toString())) {
                    Log.d(TAG, "onClick: AICI");
                    passwordErrorMessage.setVisibility(View.INVISIBLE);
                    CreateUserActionMessage actionMessage = new CreateUserActionMessage();
                    CreateUserRequest request = new CreateUserRequest();
                    request.setFirstName(firstNameEditText.getText().toString());
                    request.setLastName(lastNameEditText.getText().toString());
                    request.setEmail(emailEditText.getText().toString());
                    request.setPassword(passwordEditText.getText().toString());
                    actionMessage.setRequestBody(request);
                    BusProvider.bus().post(new CreateUserAction.OnLoadingStart(actionMessage));
                } else {
                    Log.d(TAG, "onClick: NU AICI");
                    passwordErrorMessage.setVisibility(View.VISIBLE);
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


    @Subscribe public void onLoadingSuccesful(CreateUserAction.OnLoadedSuccess response) {
        Log.d(TAG, "onLoadingSuccessful: " + response.toString());
        Intent loginActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(loginActivityIntent);
    }

    @Subscribe
    public void onLoadingFailed(CreateUserAction.OnLoadingError response) {
        Log.d(TAG, "onLoadingError: " + response.getCode());
    }

}
