package com.example.fragment.Wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragment.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class WIFItcpActivity extends AppCompatActivity {
    private final String TAG="WIFItcpActivity";
    private Button tcpsend,tcpreceive;
    private EditText tcpsentext,tcpreceivetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifitcp);
        initView();
    }

    private void initView() {
        tcpsend=findViewById(R.id.tcpsend);
        tcpsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTCPClient();
            }
        });
        tcpreceive=findViewById(R.id.tcprecive);
        tcpreceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTcpService();
            }
        });
    }
    private void startTcpService(){
        final int port=8989;
        new Thread(){
            @Override
            public void run() {
                ServerSocket serverSocket=null;
                try {
                    serverSocket=new ServerSocket(port);
                    serverSocket.setSoTimeout(8000);
                    Socket clent=serverSocket.accept();
                    Log.e(TAG,"服务器端启动");
                    //获取输入输出流
                    InputStream in=clent.getInputStream();
                    OutputStream os=clent.getOutputStream();
                    //读取数据
                    byte[] data=new byte[1024];
                    int len=in.read(data);
                    final String receiveData=new String(data,0,len, Charset.forName("UTF-8"));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG,"数据读取成功");
                            tcpreceivetext=findViewById(R.id.tcpreciveText);
                            Log.e(TAG,"数据为"+receiveData);
                            tcpreceivetext.setText(receiveData);
                        }
                    });
                    //发送相应数据
                    byte[] receive="你好，客户端，我是服务器".getBytes(Charset.forName("UTF-8"));
                    os.write(receive,0,receive.length);
                } catch (IOException e) {
                    Log.e(TAG,"数据读取失败");
                    e.printStackTrace();
                }
                finally {
                    if (serverSocket !=null) {
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
    private void startTCPClient(){
        final String host="192.168.200.183";
        final int post =8989;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket=null;

                try {
                    //第一种方式创立连接
                    socket =new Socket(host,post);

                    /**第二种方式连接
                    /* * netSocketAddress address=new InetSocketAddress(host,post);
                     socket.connect(address,5000);
                     * */
                    if (socket.isConnected()) {
                        Log.e(TAG,"连接成功");
                    }else {
                        Log.e(TAG,"连接失败");
                    }
                        //设置独流的超时时间
                        socket.setSoTimeout(8000);
                        socket.setKeepAlive(true);
                        //获取输入输出流
                        InputStream in=socket.getInputStream();
                        OutputStream os=socket.getOutputStream();
                        //发送数据
                        tcpsentext=findViewById(R.id.tcpsendText);
                        byte[] senddata=tcpsentext.getText().toString().getBytes(Charset.forName("UTF-8"));
                        os.write(senddata,0,senddata.length);
                        os.flush();
                        Log.e(TAG,"发送数据成功");

                        byte[] buf=new byte[1024];
                        int len =in.read(buf);
                        final String receivedata=new String(buf,0,len,Charset.forName("UTf-8"));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tcpsentext.setText(receivedata);
                                Log.e(TAG,"获取相应数据成功，相应数据为"+receivedata);
                            }
                        });
                } catch (IOException e) {
                    Log.e(TAG,"连接失败");
                    e.printStackTrace();
                }finally {
                    if (socket!=null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
