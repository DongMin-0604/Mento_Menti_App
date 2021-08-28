package com.example.mento_menti_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BadEndingActivity extends AppCompatActivity {
    Button Go_Home_BT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_ending);
        Go_Home_BT = findViewById(R.id.go_home_bt);

        Go_Home_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BadEndingActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
