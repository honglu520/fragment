package com.example.fragment.Recycler.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fragment.R;
import com.example.fragment.Recycler.Adater.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.fragment.Recycler.Adater.MyAdapter.*;

public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Mydate> mdata;
    private String TAG="RecyclerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView=findViewById(R.id.Recycyler);
        initdata();
        setAdater();
    }
        MyViewHolerClicks myViewHolerClicks=new MyViewHolerClicks() {


            @Override
            public void onItemClick(int position) {

                Toast.makeText(RecyclerActivity.this,"点击了第"+position+"头像",Toast.LENGTH_SHORT).show();
            }
        };


    private void setAdater() {
        LinearLayoutManager manager=new LinearLayoutManager(this);//布局管理器
        recyclerView.setLayoutManager(manager);
        MyAdapter myAdapter=new MyAdapter(mdata);//创建适配器并把数据传入进去
        recyclerView.setAdapter(myAdapter);//设置适配器
        myAdapter.setMyViewHolerClicks(myViewHolerClicks);//点击事件
    }

    private void initdata() {
        mdata=new ArrayList<>();
        for (int i = 0; i < DATA.icon.length; i++) {
            Mydate mydate=new Mydate();
            mydate.icon=DATA.icon[i];//设置每个条目的图片
            mydate.name="我是第"+ i +"个条目";//设置每个条目的文字
            mdata.add(mydate);
        }
    }
}
