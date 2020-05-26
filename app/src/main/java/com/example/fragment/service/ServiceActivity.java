package com.example.fragment.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fragment.R;

public class ServiceActivity extends AppCompatActivity {
    private String TAG="ServiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        startService1();
        stopService1();
    }

    public void startService1() {
        startService(new Intent(this, Service1.class));
        Intent intent=new Intent();
        intent.setClass(this,Service1.class);
        startService(intent);
        Log.d(TAG,"启动服务");
    }
    public void stopService1(){
        stopService(new Intent(this,Service1.class));
        Log.d(TAG,"停止服务");
    }
}
