package com.example.mento_menti_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private long backKeyPressedTime;
    private Toast toast;
    private Toast toast2;
    Button Win_Batting_BT,Draw_Batting_BT,lose_Batting_BT;
    Button Batting_Back_BT,Batting_Next_BT;
    LinearLayout Batting_layout;
    TextView Batting_Text,Batting_token_TV,Home_token_TV;
    EditText Batting_Edit_Text;
    String Win_draw_lose = "";
    String token_value,return_token;
    String return_Batting_token;
    int token = 500;
    int num1;
    boolean Batting_check;
    boolean first_check;
    boolean Happy_ending_check = false,Bad_ending_check = false;
    SharedPreferences sp;
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

        sp = getSharedPreferences("token_save",MODE_PRIVATE);
        token = sp.getInt("save",500);
        first_check = sp.getBoolean("first_check",false);

        Intent intent = getIntent();
        return_token = intent.getStringExtra("return_token_value");
        Batting_check = intent.getBooleanExtra("return_boolean_value1",false);
        first_check = intent.getBooleanExtra("first_check",false);
        return_Batting_token = intent.getStringExtra("return_Batting_token");
        Happy_ending_check = intent.getBooleanExtra("return_happy_ending_check",false);
        Bad_ending_check = intent.getBooleanExtra("return_bad_ending_check",false);
        if (savedInstanceState != null){
            token = savedInstanceState.getInt("token!");
            first_check = savedInstanceState.getBoolean("first");
        }
        if (first_check == false){
            GameStart();
        }else {
            token = intent.getIntExtra("token3",500);
            Log.d("1","리턴 토큰값: "+ token);
        }
        if (Happy_ending_check == true || Bad_ending_check == true){
            token = 500;
//          엔딩 보고 홈으로 올 시 토큰값 초기화
        }
        GameStart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int token_save = token;
        boolean first_check_save = first_check;
        outState.putInt("token!",token_save);
        outState.putBoolean("first",first_check_save);

    }

    public void GameStart(){
        Batting_token_TV.setText(Integer.toString(token));
        Home_token_TV.setText(Integer.toString(token));
        Win_Batting_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batting_layout.setVisibility(View.VISIBLE);
                Batting_Text.setText("승리");
                Draw_Batting_BT.setEnabled(false);
                lose_Batting_BT.setEnabled(false);
                Win_draw_lose = "승리";
                Batting_Edit_Text.setText("");

            }
        });
        Draw_Batting_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batting_layout.setVisibility(View.VISIBLE);
                Batting_Text.setText("무승부");
                Win_Batting_BT.setEnabled(false);
                lose_Batting_BT.setEnabled(false);
                Win_draw_lose = "무승부";
                Batting_Edit_Text.setText("");

            }
        });
        lose_Batting_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batting_layout.setVisibility(View.VISIBLE);
                Batting_Text.setText("패배");
                Draw_Batting_BT.setEnabled(false);
                Win_Batting_BT.setEnabled(false);
                Win_draw_lose = "패배";
                Batting_Edit_Text.setText("");
            }
        });
        Batting_Back_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batting_layout.setVisibility(View.GONE);
                Batting_Text.setText("");
                Batting_Edit_Text.setText("");
                Win_draw_lose = "";
                Draw_Batting_BT.setEnabled(true);
                Win_Batting_BT.setEnabled(true);
                lose_Batting_BT.setEnabled(true);
            }
        });
        Batting_Next_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                token_value = Batting_Edit_Text.getText().toString();
                if (!TextUtils.isEmpty(token_value)) {
                    num1 = Integer.parseInt(token_value);
                }
                if (num1 <= 0 || TextUtils.isEmpty(token_value)|| num1 > token){
                    toast2 = Toast.makeText(getApplicationContext(),"잘못된 입력입니다.",Toast.LENGTH_SHORT);
                    toast2.show();
                }else{
                    Batting_layout.setVisibility(View.GONE);
                    Draw_Batting_BT.setEnabled(true);
                    Win_Batting_BT.setEnabled(true);
                    lose_Batting_BT.setEnabled(true);
                    Intent intent = new Intent(MainActivity.this, RunningActivity.class);
                    intent.putExtra("token_value",token_value);
                    intent.putExtra("token_2",token);
                    intent.putExtra("win_draw_lose_value",Win_draw_lose);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            moveTaskToBack(true);
            finish();
            toast.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sp = getSharedPreferences("token_save",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("save",token);
        editor.putBoolean("first_check",first_check);
        editor.apply();
        editor.commit();

    }
}