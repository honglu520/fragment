package com.example.fragment.Webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.fragment.R;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebSettings;

public class videoActivity extends AppCompatActivity {
    private com.tencent.smtt.sdk.WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        webView =  findViewById(R.id.video);
        WebSettings webSettings=webView.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new videoActivity.AndroidAndJsInterface(),"Android");
        webView.loadUrl("file:///android_asset/JsCallJavaCallPhone.html");
    }

    class AndroidAndJsInterface {

        @JavascriptInterface
        public void call(String phone) {

            //拨打电话的意图
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(videoActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            startActivity(intent);

            //2.加拨打电话的权限


        }


        @JavascriptInterface
        public void showcontacts() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(videoActivity.this, "调用Java成功", Toast.LENGTH_SHORT).show();
                    //用于加载联系人的数据
                    String json = "[{\"name\":\"阿福\", \"phone\":\"18600012345\"}]";
                    // 调用JS中的方法
                   /**
                    * webview.loadurl的加载是在另一个线程中执行必须要在webview加载完毕后执行
                    * 如
                            * webview.loadUrl("file:///android_asset/test.html");
                            *
                            * webview.loadUrl("javascript:function()")；
                    * 的方式会导致第二句效果看不到，
                    * 原因：loadUrl是异步执行的，有可能第二局后发先至
                     */
                    if(Build.VERSION.SDK_INT<18){
                        webView.loadUrl("javascript:show('" + json + "')");
                    }else {
                        webView.evaluateJavascript("javascript:show('" + json + "')", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String s) {
                                //此处为js返回结果
                                Toast.makeText(videoActivity.this, "使用第二种调用方法成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            });

        }
    }
}
