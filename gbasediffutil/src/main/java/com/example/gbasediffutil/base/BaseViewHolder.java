package com.example.gbasediffutil.base;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：G
 * 时间：2020/4/22  17:18
 * 概述：ViewHolder父类
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private SparseArray<View> views = new SparseArray<>();
    View rootView;

    public BaseViewHolder(@NonNull View itemView, Context mcontext) {
        super(itemView);
        this.mContext = mcontext;
        rootView = itemView;

    }

    public <T extends View> T getView(int viewId) {
        T view = (T) views.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public  BaseViewHolder setText(int viewId, String msg) {
        TextView txtview = (TextView) getView(viewId);
        txtview.setText(msg);
        return this;
    }

    public  BaseViewHolder setTextHtml(int viewId, String msg) {
        TextView txtview = (TextView) getView(viewId);
        txtview.setText(Html.fromHtml(msg));
        return this;
    }

    public  BaseViewHolder setText(int viewId, Spanned msg) {
        TextView txtview = (TextView) getView(viewId);
        txtview.setText(msg);
        return this;
    }

    public  BaseViewHolder setFromHtml(int viewId, String msg) {
        TextView txtview = (TextView) getView(viewId);
        txtview.setText(msg);
        return this;
    }

    public  BaseViewHolder setImageResource(int viewId, int resource) {
        ImageView imageView = (ImageView) getView(viewId);
        imageView.setImageResource(resource);
        return this;
    }

    //设置文本是否显示
    public  BaseViewHolder setVisibilityText(int viewId, int resource) {
        TextView textView = (TextView) getView(viewId);
        textView.setVisibility(resource);
        return this;
    }

    public void setClick(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getView(id).setOnClickListener(listener);
        }
    }

    public void setLongClick(View.OnLongClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getView(id).setOnLongClickListener(listener);
        }
    }

    public View getRootView() {
        return rootView;
    }

    //设置文本颜色
    public  BaseViewHolder setTextColor(int viewId, int colorid) {
        TextView txtview = (TextView) getView(viewId);
        txtview.setTextColor(colorid);
        return this;
    }


}
