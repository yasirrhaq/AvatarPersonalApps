package com.example.papbavatarpersonalapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class AvatarActivity extends AppCompatActivity {
    private Button profileButton;
    private int[] checkboxIds = {R.id.checkbox_eye, R.id.checkbox_mouth, R.id.checkBox_brow, R.id.checkBox_nose},
            viewsToHide = {R.id.imageView_eye, R.id.imageView_mouth, R.id.imageView_brow, R.id.imageView_nose};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        hideViewsWithCheckbox(checkboxIds, viewsToHide);
        showProfile();
    }

    protected void showProfile() {
        profileButton = findViewById(R.id.button_profile);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvatarActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
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
}