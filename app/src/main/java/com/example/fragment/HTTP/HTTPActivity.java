package com.example.fragment.HTTP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.Fragment.MainActivity;
import com.example.fragment.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTPActivity extends AppCompatActivity {
    private String TAG="HTTPActivity";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        new http().start();
    }

    private void asyncGet() {
        String url= "https://www.sunofbeach.net/c/1197725454275039232";
        //第一步创建client,可以理解为创建浏览器
        OkHttpClient okHttpClient=new OkHttpClient();
        //第2步创建请求内容
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        //第3步用浏览器创建调用任务
        Call call=okHttpClient.newCall(request);
        //执行任务
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG,"onFailure -- > " + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                final String responseBody = response.body().string();
               /* final Handler handler=new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if (msg.what==123) {
                            textView=findViewById(R.id.httpshow);
                            try {
                                textView.setText(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message msg =new Message();
                        msg.what=123;
                        handler.sendMessage(msg);
                    }},0,800);*/

                Log.d(TAG,"response -- > " + responseBody);
                HTTPActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        textView=findViewById(R.id.httpshow);
                        textView.setText("请求的数据为" + responseBody);
                    }
                });

            }
        });
    }
    class http extends Thread{
        @Override
        public void run() {
            super.run();
            asyncGet();
        }
    }


}
