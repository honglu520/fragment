package com.example.fragment.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.example.fragment.R;

import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

public class WyBaseFragment extends Fragment {

    private FrameLayout mContent;
    private View mTitleContent;
    protected Activity mActivity;
    private ImageView rightIV;
    private TextView titleTV;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_fragment_title_layout,container,false);
        mContent = view.findViewById(R.id.content);
        mTitleContent = view.findViewById(R.id.titleContent);
        view.findViewById(R.id.backIg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPress();
            }
        });
        rightIV = view.findViewById(R.id.rightBtn);
        rightIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightPress();
            }
        });
        titleTV = view.findViewById(R.id.text_title);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    protected void setContentView(int layoutId){
        mContent.removeAllViews();
        mContent.addView(LayoutInflater.from(mActivity).inflate(layoutId,null));
    }

    /**
     * 展示loading窗
     */
    protected void showLoadingView(String msg){
        if(TextUtils.isEmpty(msg)){
           // msg = getResources().getString(R.string.request_data_in_process);
        }
        if(progressDialog == null){
            progressDialog = ProgressDialog.show(mActivity, "", msg, true);
            progressDialog.setCancelable(true);
        }else{
            progressDialog.show();
        }
    }

    /**
     * 隱藏loading窗
     */
    protected void dismissLoadingView(){
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
    //返回
    protected void onBackPress(){
        mActivity.finish();
    }

    //頭部右侧按钮点击事件
    protected void onRightPress(){

    }

    /**
     * 设置页面标题
     * @param title
     */
    protected void setTitle(String title){
        titleTV.setText(title);
    }
     /**
     * 设置头部背景颜色
     * @param color
     */
    protected void setTitleBgColor(int color){
        mTitleContent.setBackgroundColor(color);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置页面标题
     * @param resId
     */
    protected void setTitle(int resId){
        titleTV.setText(mActivity.getResources().getString(resId));
    }

    //頭部右侧按钮设置图标
    protected void setRightImage(int resId){
        rightIV.setBackgroundResource(resId);
    }

    //隐藏右侧图标
    protected void hideRightImage(){
        rightIV.setVisibility(View.GONE);
    }
    protected void showRightImage(){
        rightIV.setVisibility(View.VISIBLE);
    }

}
