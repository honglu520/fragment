package com.example.fragment.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.fragment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class ToolbarActivity extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        onclick();
    }

    private void onclick() {
        /**出现类型不匹配是导入的包错了
         * //应该选这一个
         import androidx.appcompat.widget.Toolbar;
         //删掉下面这个
         import android.widget.Toolbar;
         */
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdrawerLayout=findViewById(R.id.dl);
        navigationView=findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        }
        navigationView.setCheckedItem(R.id.nav_setting);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mdrawerLayout.closeDrawers();
                //这边如果报错是因为手机没有获取权限，需要手动获取
                Toast.makeText(ToolbarActivity.this,"准备跳转到拨号界面",Toast.LENGTH_SHORT).show();
                //直接打电话
                // Intent intent=new Intent(Intent.ACTION_CALL);
                Intent intent=new Intent(Intent.ACTION_DIAL);//调起拨号面板
                intent.setData(Uri.parse("tel:18365598667"));
                startActivity(intent);
                return true;
            }
        });
        //悬浮按钮点击事件
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"不删除",Snackbar.LENGTH_SHORT)
                        .setAction("删除", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(ToolbarActivity.this,"点击了悬浮按按钮",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mdrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                break;
            case R.id.delete:
                break;
            case R.id.setting:
               /* Intent intent = new Intent();
                intent.setClass(ToolbarActivity.this,DrawerActivity.class);
                startActivity(intent);*/
                break;


        }
        return true;
    }
}
