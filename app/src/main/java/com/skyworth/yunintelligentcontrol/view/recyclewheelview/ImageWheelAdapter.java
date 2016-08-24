package com.skyworth.yunintelligentcontrol.view.recyclewheelview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Sky000 on 2016/8/24.
 */
public class ImageWheelAdapter<T> extends RecyclerView.Adapter<ImageWheelAdapter.MyViewHolder> {

    List<T> mImageList;
    int mImageId;

    Context mContext;

    public ImageWheelAdapter(Context context) {
        mContext = context;
    }


    public void setBackgroundResource(int resId) {
        resId = mImageId;
    }

    public void setData(List<T> dataList) {
        mImageList = dataList;
        notifyDataSetChanged();
    }

    public void addData(List<T> dataList) {
        mImageList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new MyViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(ImageWheelAdapter.MyViewHolder holder, int position) {
        holder.iv.setBackgroundResource(Integer.parseInt(mImageList.get(position).toString()));
    }

    @Override
    public int getItemCount() {
        return mImageList == null ? 0 : mImageList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView;
        }
    }
}
