package com.android.base.base;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 17:10
 * desc    : 适配器的基类
 * modify  :
 * version : 1.0
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> data = new ArrayList<>();
    private BaseActivity activity;
    private Handler handler = new Handler(Looper.getMainLooper());
    private OnItemClickListener onItemClickListener;

    public BaseAdapter(BaseActivity activity) {
        this.activity = activity;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(activity).inflate(getItemViewLayoutId(), null));
    }

    @Override
    public abstract void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i);

    public abstract int getItemViewLayoutId();

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<T> getData() {
        return data;
    }

    public T getItemData(int position) {
        return data.get(position);
    }

    /**
     * 设置数据源
     */
    public void setData(List<T> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据源
     */
    public void onlyAddData(List<T> data) {
        if (data != null) {
            this.data.addAll(data);
        }
    }

    /**
     * 添加数据源
     */
    public void addData(List<T> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加单个数据源
     */
    public void addData(T t) {
        if (t != null) {
            this.data.add(t);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除单个数据
     */
    public void removeData(int position) {
        this.data.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 清空整个数据源
     */
    public void clearData() {
        this.data.clear();
        notifyDataSetChanged();
    }

    public BaseActivity getActivity() {
        return activity;
    }

    public Handler getHandler() {
        return handler;
    }

    public interface OnItemClickListener {
        void OnItemClick();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

}
