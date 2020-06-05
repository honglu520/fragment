package com.example.fragment.Wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragment.R;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.charset.Charset;

public class wifiUDPActivity extends AppCompatActivity {
    private String TAG="wifiUDPActivity";
    private boolean mIsServerOn;
    private Button send,receive;
    private EditText sengtext,receivetext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_udp);
        initview();
        //receiveUDP();
    }

    private void initview() {
        send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sendUDP();
            }
        });
        receive=findViewById(R.id.recive);
        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiveUDP();
            }
        });
        sengtext=findViewById(R.id.sendText);
        receivetext=findViewById(R.id.reciveText);
    }
    private void sendUDP(){
        final String hostIP="224.0.0.1";
        final int port=8080;
        new Thread(new Runnable() {
            @Override
            public void run() {
                MulticastSocket socket=null;
                try {
                    //1.创建套接字
                    socket=new MulticastSocket();
                    //设置TTL为1，套接字发送的范围为 本地网络
                    socket.setTimeToLive(1);
                    //2.创建组播网络地址，并判断
                    InetAddress group=InetAddress.getByName(hostIP);
                    if (!group.isMulticastAddress()) {
                      Log.e(TAG,"IP地址不是组播地址");
                    }
                    //3.让套接字加入组播
                    socket.joinGroup(group);
                    //4.创建数据包，包含要发送的数据，与目标主机地址

                    byte[] data=(sengtext.getText().toString().getBytes(Charset.forName("UTF-8")));
                    DatagramPacket packet=new DatagramPacket(data,data.length,group,port);
                    socket.send(packet);
                    Log.e(TAG,"数据发送成功");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG,"数据发送失败");
                }finally {
                    if (socket!=null) {
                        socket.close();
                    }
                }
            }
        }).start();
    }

    private void receiveUDP(){
        final String group="224.0.0.1";
        final int port=8080;
        new Thread(){
            @Override
            public void run() {
                super.run();
                MulticastSocket socket=null;
                try {
                    //1.创建套接字
                    socket=new MulticastSocket(port);
                    //2.创建多组播地址，并校验
                    InetAddress groupAddr = InetAddress.getByName(group);
                    if (!groupAddr.isMulticastAddress()) {
                       Log.e(TAG,"接受数据，IP地址不是组播地址");
                        return;
                    }
                    //3.套接字加入多组播中
                    socket.joinGroup(groupAddr);
                    //4.创建数据报
                    byte[] data=new byte[1024];
                    DatagramPacket packet=new DatagramPacket(data,data.length);

                    //5.循环监听端口
                    mIsServerOn=true;
                    while (mIsServerOn){
                        Log.e(TAG,"监听端口");
                        socket.receive(packet);
                        final String race=new String(data,0,packet.getLength(), Charset.forName("UTF-8"));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG,"接受数据成功");
                                receivetext.setText(race);
                            }
                        });
                    }
                } catch (IOException e) {
                    Log.e(TAG,"接受数据失败");
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
