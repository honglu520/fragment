package com.example.fragment.Recycler.Adater;



import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.R;
import com.example.fragment.Recycler.java.Mydate;


import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.holder> {
    private final List<Mydate> mData;


    //声明接口变量
    public MyViewHolerClicks myViewHolerClicks;
    public MyAdapter(List<Mydate> mdata) {
        this.mData=mdata;
     }
     //利用接口给Recycler设置点击事件
     public interface MyViewHolerClicks {
         //item的回调方法
         void onItemClick(int position);

     }
     //声明给外界方法
    public void setMyViewHolerClicks(MyViewHolerClicks myViewHolerClicks) {
        this.myViewHolerClicks = myViewHolerClicks;
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.item,null);
        return new holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int position) {
        holder.initdata(mData.get(position));
        if (myViewHolerClicks!=null) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myViewHolerClicks.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mData.size()!=0) {
            return mData.size();
        }
        return 0;
    }

    class holder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv);
            textView=itemView.findViewById(R.id.tv123);
        }
        public void initdata(Mydate mydate){
            imageView.setImageResource(mydate.icon);
            textView.setText(mydate.name);
        }
    }
}
