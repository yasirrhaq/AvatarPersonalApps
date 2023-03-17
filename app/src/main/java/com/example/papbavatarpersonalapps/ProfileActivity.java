package com.example.papbavatarpersonalapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView fullName, firstName, lastName, phoneNumber, username, upperEmail, lowerEmail;
    private Button signOut, back, call;

    private HashMap<String, String> accountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        signOut = findViewById(R.id.button_signOut);
        back = findViewById(R.id.button_back);
        call = findViewById(R.id.button_call);

        fullName = findViewById(R.id.textView_profileFullName);
        firstName = findViewById(R.id.textView_firstNameProfile);
        lastName = findViewById(R.id.textView_lastNameProfile);
        username = findViewById(R.id.textView_usernameProfile);
        phoneNumber = findViewById(R.id.textView_phoneNumber);
        upperEmail = findViewById(R.id.textView_upperEmailProfile);
        lowerEmail = findViewById(R.id.textView_emailProfile);

        back.setOnClickListener(this);
        signOut.setOnClickListener(this);
        call.setOnClickListener(this);
        accountInfo = getAccountInformation();
        setProfileText(accountInfo);
    }

    @Override
    public void onClick(View view) {
        actionHandler(view, accountInfo.get("phoneNumber"));
    }

    protected void setProfileText(HashMap<String, String> accountInfo) {
        fullName.setText(accountInfo.get("firstName") + " " + accountInfo.get("lastName"));
        firstName.setText("First Name: " + accountInfo.get("firstName"));
        lastName.setText("Last Name: " + accountInfo.get("lastName"));
        username.setText("Username: " + accountInfo.get("username"));
        phoneNumber.setText("Phone Number: " + accountInfo.get("phoneNumber"));
        upperEmail.setText(accountInfo.get("email"));
        lowerEmail.setText("Email: " + accountInfo.get("email"));
    }

    protected void actionHandler(View view, String phoneNumber) {
        switch (view.getId()) {
            case R.id.button_back: {
                Intent intent = new Intent(ProfileActivity.this, AvatarActivity.class);
                for (Map.Entry<String, String> entry : accountInfo.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    intent.putExtra(key, value);
                }
                startActivity(intent);
            }
            break;
            case R.id.button_signOut: {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(ProfileActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.button_call: {
                Uri number = Uri.parse("tel:" + phoneNumber);
                Intent intent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(intent);
            }
        }
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

}