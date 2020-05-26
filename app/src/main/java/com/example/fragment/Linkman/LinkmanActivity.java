package com.example.fragment.Linkman;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fragment.Activity;
import com.example.fragment.Fragment.MainActivity;
import com.example.fragment.R;

import java.util.ArrayList;
import java.util.List;

public class LinkmanActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkman);
        button=findViewById(R.id.Notification);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);//获取状态栏的服务

                               String channelId = "app1";
                               /**以下代码是在Android8.0以上版本要发送通知必须配置通知频道，否则无法发送通过*/
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        NotificationChannel channel = new NotificationChannel(channelId,"app1",NotificationManager.IMPORTANCE_DEFAULT);
                                        manager.createNotificationChannel(channel);
                                   }
                                //添加通知点击活动
                Intent intent=new Intent(LinkmanActivity.this, Activity.class);
                PendingIntent pendingIntent=PendingIntent.getActivities(LinkmanActivity.this,0, new Intent[]{intent},0);

                Notification notification = new NotificationCompat.Builder(LinkmanActivity.this,channelId)
                        .setContentTitle("通知标题")
                        .setContentText("通知正文")
                        .setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.gtx)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)//通知点击事件
                        .setAutoCancel(true)//点击消失
                        .build();
                        manager.notify(1,notification);
                         }
       });
    }
}
