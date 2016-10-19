package com.ls.widget.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ls.widget.R;

import java.util.List;

/**
 * 包含点击事件的监听
 * Created by VIC1 on 2016/10/10.
 */

public class RvGeneralAdapter extends RecyclerView.Adapter<RvGeneralAdapter.MyViewHolder> {

    private List<String> data;

    // item点击监听
    public interface OnItemClickListener {
        //单机事件
        void onItemClick(View view, int position);
        //长按事件
        void onItemLongClick(View view , int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public RvGeneralAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mContent.setText(data.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return true; //这里需要消耗掉，不然就会触发单击事件
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mContent;
        public MyViewHolder(View itemView) {
            super(itemView);
            mContent= (TextView) itemView.findViewById(R.id.content);
        }
    }

}
