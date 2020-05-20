package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragment.Contact.contact;
import com.example.fragment.Fragment.MainActivity;
import com.example.fragment.udp.UDPActivity;

public class Activity extends AppCompatActivity {
    private Button button1,button2,button3;

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
            }
        });
    }
}
