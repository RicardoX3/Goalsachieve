package com.example.richard.goals_achieve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.*;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView tv_time;
    private static final int msgKey1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_time = (TextView) findViewById(R.id.now_time);
        new TimeThread().start();
    }
    public class TimeThread extends  Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                    Thread.sleep(1000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    tv_time.setText(format.format(date));
                    break;
                default:
                    break;
            }
        }
    };
}
