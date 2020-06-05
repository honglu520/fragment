package com.example.fragment.Webview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import android.webkit.WebChromeClient;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.example.fragment.R;


public class callActivity extends AppCompatActivity {
    private WebView webView;
    private String name="honglu";
    public static final String TAG="callActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        webView=findViewById(R.id.call);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            //开始加载的操作
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d(TAG,"资源开始加载");

            }
            //加载结束的操作
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG,"加载完成");
                super.onPageFinished(view, url);
            }
            //处理加载页面出错时处理，（如404）调用
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                switch (errorCode){
                    case 404:
                        view.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");
                        break;
                }
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            //获得网页加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress<100){
                    Log.d(TAG,"加载到"+newProgress);
                }else if (newProgress==100){
                    Log.d(TAG,"加载到完成");
                }

            }
            //获取网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.d(TAG,"当前标题为"+title);

            }
        });


        //webView.addJavascriptInterface(new AndroidAndJsInterface(),"Android");
        webView.loadUrl("https://blog.csdn.net/w15321970103/article/details/75635454?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-12.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-12.nonecase");

        //setContentView(webView);
    }

    /**是否可以后退
     webView.canGoBack();
     后退网页
     webView.goBack();
     是否前进
     webView.canGoForward();
     前进网页
     webView.goForward();

     以当前的index为起点前进或者后退到历史记录中指定的steps
     若steps为负数则为后退，正数为前进
     webView.goBackOrForward(intsteps);*/


    //点击返回后，是网页回退而不是浏览器退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class AndroidAndJsInterface {

    }
}
