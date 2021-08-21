package com.example.mento_menti_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class RunningActivity extends AppCompatActivity {
    Button Go_Home_BT;
    private Toast toast;
    LinearLayout holding_token_Li;
    TextView Win_draw_lose_TV,left_Rock_Paper_Scissors_TV,right_Rock_Paper_Scissors_TV;
    TextView Betting_Value_TV,Batting_token_Value_TV;
    TextView holding_token_TV,Betting_history_TV;
    String game = "on",com = "가위",cheolsu = "가위";
    String value_temp;
    ImageView left_img,right_img;
    int com_int,com_int_temp = 0;
    int cheolsu_int,cheolsu_int_temp = 0;
    Handler mHandler = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_running);
        Go_Home_BT = findViewById(R.id.go_home_bt);
        left_img = findViewById(R.id.left_img);
        right_img = findViewById(R.id.right_img);
        Win_draw_lose_TV = findViewById(R.id.Win_Draw_lose_TV);
        left_Rock_Paper_Scissors_TV = findViewById(R.id.left_Rock_Paper_Scissors_TV);
        right_Rock_Paper_Scissors_TV = findViewById(R.id.right_Rock_Paper_Scissors_TV);
        Betting_Value_TV = findViewById(R.id.Batting_Value_TV);
        Batting_token_Value_TV = findViewById(R.id.Batting_token_Value_TV);
        Betting_history_TV = findViewById(R.id.Betting_history);
        holding_token_Li = findViewById(R.id.holding_token_Li);
        Go_Home_BT.setEnabled(false);


        Random random = new Random();
        com_int_temp = random.nextInt(1500)+1;
        cheolsu_int_temp = random.nextInt(1500)+1;
        com_int = com_int_temp;
        cheolsu_int = cheolsu_int_temp;
        Go_Home_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RunningActivity.this,MainActivity.class);
                finish();
                mHandler.removeMessages(0);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (game != "off"){
                    com_int++;
                    cheolsu_int++;
                    com_int = com_int%3;
                    cheolsu_int = cheolsu_int%3;
                    img_play(com_int,cheolsu_int);
                }
                mHandler.sendEmptyMessageDelayed(0,100);
            }
        };
        mHandler.sendEmptyMessage(0);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.removeMessages(0);
                Win_draw_lose_TV.setText(value_temp);
                left_Rock_Paper_Scissors_TV.setText(com);
                right_Rock_Paper_Scissors_TV.setText(cheolsu);

            }
        },5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Betting_history_TV.setVisibility(View.VISIBLE);
                Betting_Value_TV.setVisibility(View.VISIBLE);
                holding_token_Li.setVisibility(View.VISIBLE);
                Go_Home_BT.setEnabled(true);
            }
        }, 6000);
    }

    private void img_play(int com_int, int cheolsu_int) {
        switch (com_int){
            case 0:
                left_img.setImageResource(R.drawable.left_rock);
                com = "바위";
                play_result(com,cheolsu);
                break;
            case 1:
                left_img.setImageResource(R.drawable.left_scissors);
                com = "가위";
                play_result(com,cheolsu);
                break;
            case 2:
                left_img.setImageResource(R.drawable.left_paper);
                com = "보";
                play_result(com,cheolsu);
                break;
        }
        switch (cheolsu_int){
            case 0:
                right_img.setImageResource(R.drawable.right_rock);
                cheolsu = "바위";
                play_result(com,cheolsu);
                break;
            case 1:
                right_img.setImageResource(R.drawable.right_scissors);
                cheolsu = "가위";
                play_result(com,cheolsu);
                break;
            case 2:
                right_img.setImageResource(R.drawable.right_paper);
                cheolsu = "보";
                play_result(com,cheolsu);
                break;
        }
    }

    private void play_result(String com, String cheolsu) {
        if (cheolsu == "가위"){
            if (com == "가위"){
                value_temp = "무승부!";
            }
            if (com == "바위"){
                value_temp = "철수 패!";
            }
            if (com == "보"){
                value_temp = "철수 승!";
            }
        }
        if (cheolsu == "바위"){
            if (com == "가위"){
                value_temp = "철수 승!";
            }
            if (com == "바위"){
                value_temp = "무승부!";
            }
            if (com == "보"){
                value_temp = "철수 패!";
            }
        }
        if (cheolsu == "보"){
            if (com == "가위"){
                value_temp = "철수 패!";
            }
            if (com == "바위"){
                value_temp = "철수 승!";
            }
            if (com == "보"){
                value_temp = "무승부!";
            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
            toast = Toast.makeText(this,"게임진행 창에서는 앱을 종료하실 수 없습니다.",Toast.LENGTH_SHORT);
            toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
    }
}
