package com.example.papbavatarpersonalapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    Intent intent = new Intent(MainActivity.this, AvatarActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please fill Username & Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}