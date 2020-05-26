package com.example.fragment.Broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragment.R;

public class BroadcastActivity extends AppCompatActivity {
   private IntentFilter intentFilter;
   private MyBraodcast myBraodcast;
   private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        onclick();
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        myBraodcast=new MyBraodcast();
        registerReceiver(myBraodcast,intentFilter);

    }

    private void onclick() {
        button=findViewById(R.id.BREADCAST);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(BroadcastActivity.this,Broadcast2Activity.class);
                startActivity(intent);
            }
        });
    }

    //继承BroadcastReceiver重写onReceive方法，用于接受广播
    class MyBraodcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null && networkInfo.isAvailable()) {
                Toast.makeText(BroadcastActivity.this,"有网络",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(BroadcastActivity.this,"没有网络",Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(BroadcastActivity.this,"网络发生变化",Toast.LENGTH_SHORT).show();
        }
    }
}
