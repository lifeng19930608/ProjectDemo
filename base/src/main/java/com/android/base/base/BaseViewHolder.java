package com.android.base.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 17:08
 * desc    : ViewHolder的基类
 * modify  :
 * version : 1.0
 */

public class BaseViewHolder extends RecyclerView.ViewHolder{

    private final SparseArray<View> views = new SparseArray<>();

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public <TView extends View> TView getView(int resId) {
        View view = views.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            views.put(resId, view);
        }
        return (TView) view;
    }

    public void setText(int viewId, CharSequence value) {
        TextView textView = getView(viewId);
        textView.setText(value);
    }

    public void setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
    }

    public void setTextSize(int viewId, float size) {
        TextView textView = getView(viewId);
        textView.setTextSize(size);
    }

    public void setImageResource(int viewId, int resId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
    }

    public void setBackgroundResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
    }

    public void setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
    }

    public void setVisible(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
    }

}
