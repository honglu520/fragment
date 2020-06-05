package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.fragment.Bluetooth.BlueActivity;
import com.example.fragment.Bluetooth.BluetoothActivity;
import com.example.fragment.Broadcast.BroadcastActivity;
import com.example.fragment.Camera.CameraActivity;
import com.example.fragment.Contact.contact;
import com.example.fragment.Fragment.FragmentActivity;
import com.example.fragment.Fragment.MainActivity;
import com.example.fragment.Gesture.GestureActivity;
import com.example.fragment.HTTP.HTTPActivity;
import com.example.fragment.Linkman.LinkmanActivity;
import com.example.fragment.Plot.PlotActivity;
import com.example.fragment.Recycler.java.RecyclerActivity;
import com.example.fragment.SqList.HLActivity;
import com.example.fragment.SqList.MyDatabaseHelper;
import com.example.fragment.SqList.SQListActivity;
import com.example.fragment.Toolbar.ToolbarActivity;
import com.example.fragment.Webview.WebActivity;

import com.example.fragment.Wifi.wifiActivity;
import com.example.fragment.Wifi.wifiUDPActivity;
import com.example.fragment.login.loginActivity;
import com.example.fragment.service.Service2_Activity;
import com.example.fragment.service.ServiceActivity;
import com.example.fragment.udp.UDPActivity;

public class Activity extends AppCompatActivity {
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13
            ,button14,button15,button16,button17,button18,button19,button20,button21,button22,button23,button24,button25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
        onclick();
    }

    private void onclick() {
        button1=findViewById(R.id.bu1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        button2=findViewById(R.id.bu2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, UDPActivity.class);
                startActivity(intent);
            }
        });
        button3=findViewById(R.id.bu3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, contact.class);
                startActivity(intent);
                /**
                 * 第一个参数是从一个Activity跳转到第二个Activity，进入第二个Activity的动画效果
                 * 第二个参数是从一个Activity跳转到第二个Activity，离开Activity的动画效果
                 *                  系统自带两种动画效果
                 *
                 *                 // 淡入淡出的动画效果
                 *                 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                 *
                 *                // 从左向右滑动的效果
                 *                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
             */
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        button4=findViewById(R.id.bu4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, HTTPActivity.class);
                startActivity(intent);
                /**
                 * 第一个参数是从一个Activity跳转到第二个Activity，进入第二个Activity的动画效果
                 * 第二个参数是从一个Activity跳转到第二个Activity，离开Activity的动画效果
                 *                  系统自带两种动画效果
                 *
                 *                 // 淡入淡出的动画效果
                 *                 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                 *
                 *                // 从左向右滑动的效果
                 *                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                 */
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        button5=findViewById(R.id.bu5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, loginActivity.class);
                startActivity(intent);
            }
        });
        button6=findViewById(R.id.bu6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });
        button7=findViewById(R.id.bu7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, Service2_Activity.class);
                startActivity(intent);
            }
        });
        button8=findViewById(R.id.bu8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, PlotActivity.class);
                startActivity(intent);
            }
        });
        button9=findViewById(R.id.bu9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, GestureActivity.class);
                startActivity(intent);
            }
        });
        button10=findViewById(R.id.bu10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });
        button11=findViewById(R.id.bu11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, BroadcastActivity.class);
                startActivity(intent);
            }
        });
        button12=findViewById(R.id.bu12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, LinkmanActivity.class);
                startActivity(intent);
            }
        });
        button13=findViewById(R.id.bu13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, SQListActivity.class);
                startActivity(intent);
            }
        });
        button14=findViewById(R.id.bu14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, HLActivity.class);
                startActivity(intent);
            }
        });
        button15=findViewById(R.id.bu15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        button16=findViewById(R.id.bu16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, ToolbarActivity.class);
                startActivity(intent);
            }
        });
        button17=findViewById(R.id.bu17);
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, FragmentActivity.class);
               //startActivity(intent);
            }
        });
        button18=findViewById(R.id.bu18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, WebActivity.class);
                startActivity(intent);
            }
        });
        button19=findViewById(R.id.bu19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, BlueActivity.class);
                startActivity(intent);
            }
        });
        button20=findViewById(R.id.bu20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, BluetoothActivity.class);
                startActivity(intent);
            }
        });
        button21=findViewById(R.id.bu21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(Activity.this, wifiActivity.class);
                startActivity(intent);
            }
        });
    }
}
