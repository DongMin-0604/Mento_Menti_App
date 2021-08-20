package com.example.mento_menti_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button Win_Batting_BT,Draw_Batting_BT,lose_Batting_BT;
    Button Batting_Back_BT,Batting_Next_BT;
    LinearLayout Batting_layout;
    TextView Batting_Text,Batting_token_TV,Home_token_TV;
    EditText Batting_Edit_Text;
    int token = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Win_Batting_BT = findViewById(R.id.win_bt);
        Draw_Batting_BT = findViewById(R.id.draw_bt);
        lose_Batting_BT = findViewById(R.id.lose_bt);
        Batting_layout = findViewById(R.id.Batting_layout);
        Batting_Text = findViewById(R.id.Batting_Text);
        Batting_Back_BT = findViewById(R.id.Batting_BackBT);
        Batting_Edit_Text = findViewById(R.id.Batting_EditText);
        Batting_token_TV = findViewById(R.id.Batting_token_TV);
        Home_token_TV = findViewById(R.id.Home_token_TV);
        Batting_token_TV.setText(Integer.toString(token));
        Home_token_TV.setText(Integer.toString(token));
        Batting_Next_BT = findViewById(R.id.Batting_NextBT);
        GameStart();
    }
    public void GameStart(){
        Win_Batting_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batting_layout.setVisibility(View.VISIBLE);
                Batting_Text.setText("승리");
                Draw_Batting_BT.setEnabled(false);
                lose_Batting_BT.setEnabled(false);
            }
        });
        Draw_Batting_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batting_layout.setVisibility(View.VISIBLE);
                Batting_Text.setText("무승부");
                Win_Batting_BT.setEnabled(false);
                lose_Batting_BT.setEnabled(false);
            }
        });
        lose_Batting_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batting_layout.setVisibility(View.VISIBLE);
                Batting_Text.setText("패배");
                Draw_Batting_BT.setEnabled(false);
                Win_Batting_BT.setEnabled(false);
            }
        });
        Batting_Back_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batting_layout.setVisibility(View.GONE);
                Batting_Text.setText("");
                Batting_Edit_Text.setText("");
                Draw_Batting_BT.setEnabled(true);
                Win_Batting_BT.setEnabled(true);
                lose_Batting_BT.setEnabled(true);
            }
        });
        Batting_Next_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batting_layout.setVisibility(View.GONE);
                Draw_Batting_BT.setEnabled(true);
                Win_Batting_BT.setEnabled(true);
                lose_Batting_BT.setEnabled(true);
                Intent intent = new Intent(MainActivity.this, RunningActivity.class);
                startActivity(intent);
            }
        });
    }
}