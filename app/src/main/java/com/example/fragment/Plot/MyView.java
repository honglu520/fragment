package com.example.fragment.Plot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;



public class MyView extends View {
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        Paint paint=new Paint();

        paint.setAntiAlias(true); //去锯齿ture表示抗锯齿，false则不需要
        paint.setColor(Color.RED);//设置画笔为红色
        paint.setStyle(Paint.Style.STROKE);//设置style，stroke表示绘制边缘，fill表示填满
        paint.setStrokeWidth(5);//设置画笔宽度
        //绘制圆形
        canvas.drawCircle(40,40,30,paint);
        //绘制正方形
        canvas.drawRect(10,80,70,140,paint);
        //绘制矩形
        canvas.drawRect(10,150,70,190,paint);
        //绘制圆角矩形
        RectF re1=new RectF(10,200,70,230);
        canvas.drawRoundRect(re1,15,15,paint);
        //绘制三角形
        Path path5=new Path();
        path5.moveTo(170,340);
        path5.lineTo(230,340);
        path5.lineTo(200,290);
        path5.close();
        canvas.drawPath(path5,paint);
        canvas.drawText("android开发",240,50,paint);

    }
}
