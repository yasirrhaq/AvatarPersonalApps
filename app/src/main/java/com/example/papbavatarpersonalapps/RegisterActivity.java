package com.example.papbavatarpersonalapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstName, lastName, username, email, password;
    private Button signUpButton;
    private TextView alreadyHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.editText_firstNameRegister);
        lastName = findViewById(R.id.editText_lastNameRegister);
        username = findViewById(R.id.editText_usernameRegister);
        email = findViewById(R.id.editText_emailAddressRegister);
        password = findViewById(R.id.editText_passwordRegister);

        handleRegister();
        makeTextClickable();
    }

    protected void makeTextClickable() {
        alreadyHaveAccount = findViewById(R.id.textView_haveAccount);
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void handleRegister() {
        signUpButton = findViewById(R.id.button_signUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(firstName.getText().toString())
                        && !TextUtils.isEmpty(lastName.getText().toString())
                        && !TextUtils.isEmpty(username.getText().toString())
                        && !TextUtils.isEmpty(email.getText().toString())
                        && !TextUtils.isEmpty(password.getText().toString())) {
                    Intent intent = new Intent(RegisterActivity.this, AvatarActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Please fill all the field!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}