package com.example.fragment.Bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fragment.R;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

public class BluetoothActivity extends AppCompatActivity {
    private  static final String TAG="BluetoothActivity";
    private BluetoothHeadset bluetoothHeadset;
    private BluetoothAdapter bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();//获取蓝牙适配器;//本地蓝牙适配器
    // 远程蓝牙设备。使用这个类来请求一个与远程设备的BluetoothSocket连接，或者查询关于设备名称、地址、类和连接状态等设备信息。
    private  BluetoothDevice bluetoothDevice;
    private Button open,close,scan;
    public ArrayAdapter arrayAdapter;
    private ListView listView;
    private BluetoothSocket bluetoothSocket;//声明蓝牙套接字

    //定义一个列表，存蓝牙设备地址
    public ArrayList< BluetoothDevice> arrayList=new ArrayList< BluetoothDevice>();
    public ArrayList<Integer>  ispairing=new ArrayList<Integer>();
    //定义一个列表，存蓝牙设备地址，用于显示
    public ArrayList<String> devicename=new ArrayList<String>();
    //注册搜索蓝牙设备的广播
    private final BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                    bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    switch (bluetoothDevice.getBondState()) {
                        case BluetoothDevice.BOND_BONDING:// 正在配对
                            Log.d(TAG,"正在配对");
                            break;
                        case BluetoothDevice.BOND_BONDED:// 配对结束
                            Log.d(TAG,"配对成功");
                            break;
                        case BluetoothDevice.BOND_NONE:// 取消配对/未配对
                            Log.d(TAG,"取消配对/未配对");
                        default:
                            break;
                    }
                    break;
            }
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                /**
                BluetoothDevice.BOND_NONE  指明远程设备未被匹配，常量值 int 10
                BluetoothDevice.BOND_BONDING;指明设备的匹配正在运行中，常量值 int 11
                BluetoothDevice.BOND_BONDED  指明设备已经匹配，常量值 int 12
                */
                if (bluetoothDevice.getBondState()==BluetoothDevice.BOND_NONE) {
                    devicename.add("设备名："+bluetoothDevice.getName()+"\n" +"设备地址："+bluetoothDevice.getAddress() + "\n"+"未配对");//将搜索到的蓝牙名称和地址添加到列表。
                }else {
                    devicename.add("设备名："+bluetoothDevice.getName()+"\n" +"设备地址："+bluetoothDevice.getAddress() + "\n"+"已配对");//将搜索到的蓝牙名称和地址添加到列表。

                }
                arrayList.add( bluetoothDevice);//将搜索到的蓝牙地址添加到列表。
                ispairing.add(bluetoothDevice.getBondState());
                arrayAdapter.notifyDataSetChanged();//更新
            }
        }
    };;
    private BluetoothProfile.ServiceListener pro=new BluetoothProfile.ServiceListener() {
        @Override
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (i== BluetoothProfile.HEADSET) {
                bluetoothHeadset= (BluetoothHeadset) bluetoothProfile;
            }
        }

        @Override
        public void onServiceDisconnected(int i) {
            if (i== BluetoothProfile.HEADSET) {
                bluetoothHeadset=null;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);//蓝牙状态改变的广播
        filter.addAction(BluetoothDevice.ACTION_FOUND);//找到设备的广播
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);//搜索完成的广播
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);//开始扫描的广播
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);//状态改变
        filter.addAction(BluetoothHeadset.ACTION_VENDOR_SPECIFIC_HEADSET_EVENT);//设备电池电量
        registerReceiver(broadcastReceiver, filter);//动态注册广播，用BroadcastReceiver 来取得结果
        initview();
        adaper();

    }



    private void adaper() {
        listView=findViewById(R.id.list);

        //定义适配器
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,devicename);
        //给列表设置适配器
        listView.setAdapter(arrayAdapter);
        //设置item点击事件,进行蓝牙配对
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                BluetoothDevice device=arrayList.get(postion);
                if (ispairing.get(postion)==BluetoothDevice.BOND_BONDED){
                    Log.d(TAG,devicename.get(postion));
                    coon(device);
                }else {
                    Method method= null;
                    try {
                        method = BluetoothDevice.class.getMethod("createBond");
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    try {
                        Log.e(TAG,"开始配对");
                        method.invoke(arrayList.get(postion));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                /*if (ispairing.get(postion)==BluetoothDevice.BOND_BONDED) {
                    Log.e(TAG,devicename.get(postion));
                    SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                    try {
                       BluetoothDevice bluetoothDevice1=arrayList.get(postion);
                       Log.e(TAG, String.valueOf(bluetoothDevice1));
                       bluetoothSocket=bluetoothDevice1.createInsecureRfcommSocketToServiceRecord(SPP_UUID);
                        new Thread(){
                            @Override
                            public void run() {
                                new Thread(){
                                    @Override
                                    public void run() {
                                        try {
                                            bluetoothSocket.connect();//这句必须要单独放在一个子线程不然会报
                                            Log.e(TAG,"连接成功");
                                            InputStream is =bluetoothSocket.getInputStream();//用于收数据
                                            OutputStream os=bluetoothSocket.getOutputStream();//用于发数据
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            Log.e(TAG,"连接错误");
                                        }
                                    }
                                }.start();
                            }
                        }.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Method method = null;
                    try {
                        method = BluetoothDevice.class.getMethod("createBond");
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG,"开始配对");
                    try {
                        method.invoke(arrayList.get(postion));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }*/
            }

            private void coon(final BluetoothDevice device) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            bluetoothSocket= device.createInsecureRfcommSocketToServiceRecord(UUID
                                    .fromString("00001101-0000-1000-8000-00805F9B34FB"));
                           // OutputStream os=bluetoothSocket.getOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                new Thread(){
                    @Override
                    public void run() {
                        if (bluetoothAdapter.isDiscovering()) {
                            bluetoothAdapter.cancelDiscovery();//取消扫描

                            // Toast.makeText(BluetoothActivity.this, "取消扫描", Toast.LENGTH_SHORT).show();
                            try {
                                Log.d(TAG,"开始连接");
                                bluetoothSocket.connect();
                            } catch (IOException ee) {
                                Log.e(TAG, String.valueOf(ee));
                                Log.d(TAG,"连接出错");
                                return;
                            }
                        }else {
                            Log.d(TAG,"准备开始连接");
                            try {
                                bluetoothSocket.connect();
                            } catch (IOException e) {

                                Log.e(TAG, String.valueOf(e));
                                Log.d(TAG,"准备连接出错");
                            }
                        }
                    }
                }.start();
            }
        });
    }

    private void initview() {
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();//获取蓝牙适配器
        open=findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断本机蓝牙是否打开
                //若没有打开则打开蓝牙
                if (!bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.enable();//开启蓝牙
                }
            }
        });
        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭蓝牙
                if (bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.disable();//关闭蓝牙
                }
            }
        });
        scan=findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bluetoothAdapter.isEnabled()) {
                    Toast.makeText(BluetoothActivity.this, "蓝牙未开启请打开蓝牙在进行搜索", Toast.LENGTH_SHORT).show();

                }else if (bluetoothAdapter.isDiscovering()){
                    bluetoothAdapter.cancelDiscovery();//取消扫描
                    Toast.makeText(BluetoothActivity.this, "取消扫描", Toast.LENGTH_SHORT).show();
                }else {
                    bluetoothAdapter.startDiscovery();//开启扫描
                    Toast.makeText(BluetoothActivity.this, "开启扫描", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"本地蓝牙名称:"+bluetoothAdapter.getName());
                    Log.d(TAG, "本机蓝牙地址：" +bluetoothAdapter.getAddress());
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

}
