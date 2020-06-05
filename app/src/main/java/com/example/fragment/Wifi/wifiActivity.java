package com.example.fragment.Wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragment.R;

public class wifiActivity extends AppCompatActivity {
    private Button tcp,udp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        tcp=findViewById(R.id.tcp);
        tcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(wifiActivity.this,WIFItcpActivity.class);
                startActivity(intent);
            }
        });
        udp=findViewById(R.id.udp);
        udp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(wifiActivity.this,wifiUDPActivity.class);
                startActivity(intent);
            }
        });
    }
}
