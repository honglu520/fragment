package com.example.fragment.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.example.fragment.R;

public class Service2_Activity extends AppCompatActivity {
    private Service2.Mybind mybind;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mybind= (Service2.Mybind) iBinder;
            mybind.getSrting();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2_);
    }


     public void start_service(View view){
         startService(new Intent(this,Service2.class));
         Toast.makeText(Service2_Activity.this,"准备启动服务2",Toast.LENGTH_SHORT).show();
     }
     public void stop_service(View view){
         stopService(new Intent(this,Service2.class));
         Toast.makeText(Service2_Activity.this,"准备停止服务2",Toast.LENGTH_SHORT).show();
     }
    public void bind_service(View view){
        bindService(new Intent(this,Service2.class),connection,BIND_AUTO_CREATE);
        Toast.makeText(Service2_Activity.this,"准备绑定服务2",Toast.LENGTH_SHORT).show();
    }
    public void unbind_service(View view){
        unbindService(connection);
        Toast.makeText(Service2_Activity.this,"准备解绑服务2",Toast.LENGTH_SHORT).show();
    }

}