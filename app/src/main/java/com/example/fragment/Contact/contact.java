package com.example.fragment.Contact;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

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
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时执行
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时执行
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复时执行

            }

        });
    }


}
