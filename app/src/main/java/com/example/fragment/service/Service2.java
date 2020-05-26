package com.example.fragment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Service2 extends Service {
    private  String TAG = "Service2";

    @Override
    public IBinder onBind(Intent intent) {
       return new Mybind();
    }
    class Mybind extends Binder{
        public void getSrting(){
            Log.d(TAG,"------->  getString");
        }
    }

    @Override
    public void onCreate() {

        Log.d(TAG,"服务2创建");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"服务2启动");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"服务2销毁");
        super.onDestroy();
    }
}
