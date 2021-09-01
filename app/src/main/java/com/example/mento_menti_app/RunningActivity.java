package com.example.mento_menti_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
    TextView Betting_history_TV;
    String game = "on",com = "가위",cheolsu = "가위";
    String value_temp,value_temp2;
    ImageView left_img,right_img;
    int com_int,com_int_temp = 0;
    int cheolsu_int,cheolsu_int_temp = 0;
    int token;
    int token1;
    int token3;
    String token_value1,W_D_L_value;
    boolean Batting_check = false;
    boolean first_check = false;
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
        Batting_check = false;

        Random random = new Random();
        com_int_temp = random.nextInt(3000)+1;
        cheolsu_int_temp = random.nextInt(3001)+1;
        com_int = com_int_temp;
        cheolsu_int = cheolsu_int_temp;

        Intent intent = getIntent();
        token_value1 = intent.getStringExtra("token_value");
        token = Integer.parseInt(token_value1);
        W_D_L_value = intent.getStringExtra("win_draw_lose_value");
        token3 = intent.getIntExtra("token_2",0);
        Log.d("1","token: "+ token3);

        Go_Home_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (token3 < 5000 && token3 > 0){
                    Intent intent = new Intent(RunningActivity.this,MainActivity.class);
                    first_check = true;
                    finish();
                    mHandler.removeMessages(0);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("return_token_value",Integer.toString(token1));
                    intent.putExtra("token3",token3);
                    intent.putExtra("return_boolean_value1",(Batting_check));
                    intent.putExtra("return_Batting_token",(token_value1));
                    intent.putExtra("first_check",(first_check));
                    Log.d("1","토큰: "+ token3);
                    Log.d("1","리턴 토큰: "+ token1);
                    Log.d("1","리턴 베팅 토큰: "+ token_value1);
                    startActivity(intent);
                }else if (token3 >= 5000){
                    Intent intent1 = new Intent(RunningActivity.this,HappyEndingAcitivity.class);
                    finish();
                    mHandler.removeMessages(0);
                    startActivity(intent1);

                }else if (token3 <= 0){
                    Intent intent2 = new Intent(RunningActivity.this,BadEndingActivity.class);
                    finish();
                    mHandler.removeMessages(0);
                    startActivity(intent2);
                }

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
        },4500);//4500
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (W_D_L_value.equals(value_temp2)){
                    token3 = token3 - token;
                    token = token * 2;
                    //토큰 = 건 값 , 토큰  3 = 총 토큰
                    Batting_check = true;
                    token3 = token3 + token;
                }else {
                    token3 = token3 - token;
                    token = 0;
                    Batting_check = false;
                }
                Betting_history_TV.setVisibility(View.VISIBLE);
                Betting_Value_TV.setVisibility(View.VISIBLE);
                holding_token_Li.setVisibility(View.VISIBLE);
                Batting_token_Value_TV.setText(Integer.toString(token));
                Go_Home_BT.setEnabled(true);
            }
        }, 5500);//5500
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
                value_temp2 = "무승부";
            }else  if (com == "바위"){
                value_temp = "철수 패!";
                value_temp2 = "패배";
            }else  if (com == "보"){
                value_temp = "철수 승!";
                value_temp2 = "승리";
            }
        }
        if (cheolsu == "바위"){
            if (com == "가위"){
                value_temp = "철수 승!";
                value_temp2 = "승리";
            }else  if (com == "바위"){
                value_temp = "무승부!";
                value_temp2 = "무승부";
            }else  if (com == "보"){
                value_temp = "철수 패!";
                value_temp2 = "패배";
            }
        }
        if (cheolsu == "보"){
            if (com == "가위"){
                value_temp = "철수 패!";
                value_temp2 = "패배";
            }else  if (com == "바위"){
                value_temp = "철수 승!";
                value_temp2 = "승리";
            }else  if (com == "보"){
                value_temp = "무승부!";
                value_temp2 = "무승부";
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
