package com.example.gbasediffutil.base;


import androidx.annotation.Nullable;

/**
 * author：G
 * time：2021/1/20 9:21
 * about：DiffCallBack
 **/

public abstract class DiffCallBack<T> {

    public abstract boolean areItemsTheSame(T oldItem, T newItem);

    public abstract boolean areContentsTheSame(T oldItem, T newItemn);

    @Nullable
    public Object getChangePayload(T oldItem, T newItem) {
        return null;
    }
}
