package com.example.papbavatarpersonalapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userNameEditText, passwordEditText;
    private Button signInButton, registButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.editText_username);
        passwordEditText = findViewById(R.id.editText_password);
        signInButton = findViewById(R.id.button_signIn);
        registButton = findViewById(R.id.button_regist);

        signInButton.setOnClickListener(this);
        registButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        actionHandler(view);
    }

    protected HashMap<String, String> getAccountInformation() {
        Intent intent = getIntent();
        HashMap<String, String> accountInfo = new HashMap<>();

        if (intent.getExtras() != null) {
            accountInfo.put("firstName", intent.getStringExtra("firstName"));
            accountInfo.put("lastName", intent.getStringExtra("lastName"));
            accountInfo.put("username", intent.getStringExtra("username"));
            accountInfo.put("phoneNumber", intent.getStringExtra("phoneNumber"));
            accountInfo.put("email", intent.getStringExtra("email"));
            accountInfo.put("password", intent.getStringExtra("password"));
        }
        return accountInfo;
    }

    protected void actionHandler(View view) {
        switch (view.getId()) {
            case R.id.button_regist: {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.button_signIn: {
                HashMap<String, String> accountInfo = getAccountInformation();
                String username = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.equals(accountInfo.get("username")) && password.equals(accountInfo.get("password"))) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    for (Map.Entry<String, String> entry : accountInfo.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        intent.putExtra(key, value);
                    }
                    startActivity(intent);
                } else if (TextUtils.isEmpty(userNameEditText.getText().toString()) || TextUtils.isEmpty(passwordEditText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please fill Username & Password!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }
}