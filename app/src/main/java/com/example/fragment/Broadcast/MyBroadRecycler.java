package com.example.fragment.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadRecycler extends BroadcastReceiver {
    private String TAG="MyBroadRecycler";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"收到了广播");
        Toast.makeText(context,"接收到了广播",Toast.LENGTH_SHORT).show();
    }
}
