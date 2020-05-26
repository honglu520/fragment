package com.example.fragment.Broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragment.R;

public class Broadcast2Activity extends AppCompatActivity {
    private Button button;
    private MyBroadRecycler myBroadRecycler;
    private static final String BROADCAST_ACTION_DISC="com.example.fragment.Broadcast.MyBroadRecycler";//包名.接受广播类名
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast2);

        button=findViewById(R.id.Broadcastshow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BROADCAST_ACTION_DISC);
                sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Android8.0以上尽量使用动态注册广播
        //第一步创建广播接受器对象
        myBroadRecycler=new MyBroadRecycler();
        //设置广播接受的类型
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION_DISC);
        //第三步动态注册广播
        registerReceiver(myBroadRecycler,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadRecycler);
    }
}
