package com.example.fragment.Contact;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.fragment.R;

public class contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initview(R.id.tv,R.anim.view_animation);
        initview(R.id.tv1,R.anim.view_animation1);
        initview(R.id.tv2,R.anim.view_animation2);
        initview(R.id.tv3,R.anim.view_animation3);
        initview(R.id.tv4,R.anim.view_animation4);

    }

    private void initview(@IdRes int id, int id1) {
        //步骤1：创建需要设置动画的视图View
        TextView textView=findViewById(id);
        //步骤2:创建动画对象，并传入设置的动画效果
        Animation animation=AnimationUtils.loadAnimation(this,id1);
        //步骤三播放动画
        textView.startAnimation(animation);
    }

   /* private void initview5() {
        //步骤1：创建需要设置动画的视图View
        TextView textView=findViewById(R.id.tv4);
        //步骤2:创建动画对象，并传入设置的动画效果
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.view_animation4);
        //步骤三播放动画
        textView.startAnimation(animation);
    }

    private void initview4() {
        //步骤1：创建需要设置动画的视图View
        TextView textView=findViewById(R.id.tv3);
        //步骤2：创建动画对象，并传入设置的动画效果
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.view_animation3);
        //步骤3:播放动画
        textView.startAnimation(animation);

    }

    private void initview3() {
        //步骤1：创建需要设置动画的视图View
        TextView textView=findViewById(R.id.tv2);
        //步骤2：创建动画对象，并传入设置动画效果的XML文件
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.view_animation2);
        //步骤3，播放动画
        textView.startAnimation(animation);
    }

    private void initview2() {
        //步骤1：创建需要设置动画的视图View
        TextView textView=findViewById(R.id.tv1);
        //步骤2：创建动画对象，并传入设置动画效果的XML文件
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.view_animation1);
        //步骤3，播放动画
        textView.startAnimation(animation);
    }

    private void initview1() {
        //步骤1：创建需要设置动画的视图View
        TextView textView=findViewById(R.id.tv);
        //步骤2：创建动画对象，并传入设置动画效果的XML文件
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.view_animation);
        //步骤3，播放动画
        textView.startAnimation(animation);
    }*/
}
