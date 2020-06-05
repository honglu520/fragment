package com.example.fragment.Webview;



import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragment.R;


public class htmlActivity extends Activity {
    private WebView webView;
    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;
    private static final String name ="honglu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_html);
        finview();
        initview();
    }

    private void finview() {
        setContentView(R.layout.activity_java_and_js_call);
        etNumber =  findViewById(R.id.et_number);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }
    private void login() {
        //1.得到账号和密码
        String number = etNumber.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //账号和密码不为空
            login(number, password);

        }
    }

    private void login(String number, String password) {

        webView.loadUrl("javascript:javaCallJs('" + number + "')");
        setContentView(webView);
    }

    private void initview() {
        //1.加载h5html自定义浏览器
        webView=new WebView(this);
        WebSettings webSettings=webView.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //不调用浏览器
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                //步骤2：根据协议的参数，判定是否所需的Url
                //一般根据scheme（协议格式）&authority（协议名）判断（前两个参数）
                Uri uri=Uri.parse(url);
                //如果url的协议=预先约定的js协议
                //就向下解析参数
                if (uri.getScheme().equals("js")){
                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")){
                        //步骤三
                        //执行js所需要调用的逻辑
                        Toast.makeText(htmlActivity.this, "使用第二种方法调用了Android方法", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                    return super.shouldOverrideUrlLoading(view,url);
            }

        });
        webView.addJavascriptInterface(new AndroidAndJsInterface(),"Android");
        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");
        //login(name,name);

        webView.setWebChromeClient(new WebChromeClient(){
            // 拦截输入框(原理同方式2)
            // 参数message:代表promt（）的内容（不是url）
            // 参数result:代表输入框的返回值
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {


                    // 根据协议的参数，判断是否是所需要的url(原理同方式2)
                    // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                    //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                    Uri uri = Uri.parse(message);
                    // 如果url的协议 = 预先约定的 js 协议
                    // 就解析往下解析参数
                    if ( uri.getScheme().equals("js")) {

                        // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                        // 所以拦截url,下面JS开始调用Android需要的方法
                        if (uri.getAuthority().equals("demo")) {

                            //
                            // 执行JS所需要调用的逻辑
                            //System.out.println("js调用了Android的方法");
                            // 可以在协议上带有参数并传递到Android上;

                            //参数result:代表消息框的返回值(输入值)
                            result.confirm("js调用了Android的方法成功啦");
                        }
                        return true;
                    }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }


        });
    }
    class  AndroidAndJsInterface{
        @JavascriptInterface
        public void showToast(){
            Toast.makeText(htmlActivity.this, "android 被调用了111", Toast.LENGTH_SHORT).show();

        }
    }
}
