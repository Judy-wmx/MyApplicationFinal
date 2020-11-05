package com.example.acer.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.sqlite.login.R;

public class MeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.anquanxinsi: {
                Intent intent = new Intent(this, AnquanyuyinsiActivity.class);
                startActivityForResult(intent, 0);
                break;
            }//anquanxinsi
            //qingxutongji
            case R.id.qingxutongji: {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, 0);
                break;
            }
            case R.id.guanyuchengxu: {
                Intent intent = new Intent(this, AppActivity.class);
                startActivityForResult(intent, 0);
                break;
            }
        }
    }
    //底部按钮事件
    public void btn3Click(View view) {
        switch (view.getId()) {

            case R.id.btn_Emotion3: {
                Intent intent = new Intent(this, EmotionActivity.class);
                startActivityForResult(intent, 0);
                break;
            }

            case R.id.btn_Find3:{
                Intent intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, 0);
                break;
            }
            case R.id.btn_Me3:{
                Intent intent = new Intent(this, MeActivity.class);
                startActivityForResult(intent, 0);
                break;
            }

        }
    }
}
