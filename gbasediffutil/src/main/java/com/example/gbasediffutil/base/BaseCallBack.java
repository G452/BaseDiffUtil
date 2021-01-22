package com.example.gbasediffutil.base;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * author：G
 * time：2021/1/19 18:13
 * about：BaseCallBack
 **/

public abstract class BaseCallBack<T> extends DiffUtil.Callback {

    protected List<T> mOldData;
    protected List<T> mNewData;

    public BaseCallBack() {

    }

    public BaseCallBack(@NonNull List<T> oldData, @NonNull List<T> newData) {
        mOldData = oldData;
        mNewData = newData;
    }

    public void setOldData(List<T> oldData) {
        mOldData = oldData;
    }

    public void setNewData(List<T> newData) {
        mNewData = newData;
    }

    @Override
    public int getOldListSize() {
        return mOldData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(mOldData.get(oldItemPosition), mNewData.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areContentsTheSame(mOldData.get(oldItemPosition), mNewData.get(newItemPosition));
    }

    public abstract boolean areItemsTheSame(T oldItem, T newItem);

    public abstract boolean areContentsTheSame(T oldItem, T newItem);
}
