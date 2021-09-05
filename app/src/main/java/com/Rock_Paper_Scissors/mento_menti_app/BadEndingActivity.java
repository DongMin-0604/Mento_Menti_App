package com.Rock_Paper_Scissors.mento_menti_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BadEndingActivity extends AppCompatActivity {
    Button Go_Home_BT;
    boolean ending_check;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_ending);
        Go_Home_BT = findViewById(R.id.go_home_bt);
        ending_check = true;

        Go_Home_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BadEndingActivity.this,MainActivity.class);
                intent.putExtra("return_bad_ending_check",(ending_check));
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        toast = Toast.makeText(this,"'홈으로' 버튼을 눌러주세요.",Toast.LENGTH_SHORT);
        toast.show();
    }
}
