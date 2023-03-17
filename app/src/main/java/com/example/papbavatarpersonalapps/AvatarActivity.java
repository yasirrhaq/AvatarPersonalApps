package com.example.papbavatarpersonalapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.HashMap;
import java.util.Map;

public class AvatarActivity extends AppCompatActivity implements View.OnClickListener {
    private Button profileButton, callMeButton;
    private HashMap<String, String> accountInfo;
    private int[] checkboxIds = {R.id.checkbox_eye, R.id.checkbox_mouth, R.id.checkBox_brow, R.id.checkBox_nose},
            viewsToHide = {R.id.imageView_eye, R.id.imageView_mouth, R.id.imageView_brow, R.id.imageView_nose};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        profileButton = findViewById(R.id.button_profile);
        callMeButton = findViewById(R.id.button_call2);

        profileButton.setOnClickListener(this);
        callMeButton.setOnClickListener(this);

        accountInfo = getAccountInformation();
        hideViewsWithCheckbox(checkboxIds, viewsToHide);
    }

    protected void hideViewsWithCheckbox(int[] checkBoxIds, int[] viewsToHide) {
        for (int i = 0; i < checkBoxIds.length; i++) {
            CheckBox cb = findViewById(checkBoxIds[i]);
            View viewToHide = findViewById(viewsToHide[i]);

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    viewToHide.setVisibility(isChecked ? View.INVISIBLE : View.VISIBLE);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        actionHandler(view, intent.getStringExtra("phoneNumber"));
    }

    protected void actionHandler(View view, String phoneNumber){
        switch(view.getId()){
            case R.id.button_profile:{
                Intent intent = new Intent(AvatarActivity.this, ProfileActivity.class);
                for (Map.Entry<String, String> entry : accountInfo.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    intent.putExtra(key, value);
                }
                startActivity(intent);
            }break;
            case R.id.button_call2:{
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