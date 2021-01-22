package com.example.gbasediffutil.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author：G
 * time：2021/1/20 9:44
 * about：BaseRecycleAdapter
 **/
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {


    protected List<T> list = new ArrayList<>();
    private Context mcontext;
    private int postion;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_BOTTOM = 3;
    public static final int TYPE_CENTER = 2;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;
    private View mCenterView;
    private View mBottomView;
    private int centerPostion = 0;
    private int listSize = 0;
    public OnRecycleItemClickLinster mOnRecycleItemClickLinster = null;
    public OnRecycleItemClickLinster2 mOnRecycleItemClickLinster2 = null;
    public OnRecycleItemClickLinster3 mOnRecycleItemClickLinster3 = null;

    public BaseRecycleAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }


    /**
     * 辅助计算.
     */
    protected DiffAdapterHelper<T> mAdapterHelper;

    /**
     * 用于首次添加数据，并设置对比参数
     **/
    public void setList(List<T> datas, DiffCallBack<T> callBack) {
        this.list = datas;
        if (list instanceof BaseListModel) {
            mAdapterHelper = new DiffAdapterHelper<>(list, this, callBack);
        }
    }

    /**
     * 用于更新list数据
     **/
    public final void updataListData(List<T> listData) {
        if (mAdapterHelper != null) {
            mAdapterHelper.setListData(listData);
        } else {
            setRealListData(listData);
        }
    }

    /**
     * 用于对比后回调的真实列表
     **/
    protected final void setRealListData(List<T> listData) {
        list = listData;
    }

    /**
     * 获取当前adapter的list
     **/
    public List<T> getlist() {
        return this.list;
    }

//    public void toast(String s) {
//        ToastUtils.showShortToast(s);
//    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new BaseViewHolder(mHeaderView, mcontext);
        if (mCenterView != null && viewType == TYPE_CENTER)
            return new BaseViewHolder(mCenterView, mcontext);
        if (mBottomView != null && viewType == TYPE_BOTTOM)
            return new BaseViewHolder(mBottomView, mcontext);
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(getLayoutId(), parent, false);
        return new BaseViewHolder(view, mcontext);
    }

    protected abstract int getLayoutId();

    protected abstract void setDataToHolder(@NonNull BaseViewHolder holder, @NonNull T t, int postion);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (getItemViewType(position) == TYPE_CENTER) return;
        if (getItemViewType(position) == TYPE_BOTTOM) return;
        final int pos = getRealPosition(holder);
        this.postion = pos;
        setDataToHolder(holder, list.get(pos), pos);
    }

    @Override
    public int getItemCount() {
        int size = list.size();
        if (mHeaderView != null) {
            size++;
        }
        if (mCenterView != null) {
            size++;
        }
        if (mBottomView != null) {
            size++;
        }
        this.listSize = size;
        return size;
    }


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setCenterView(View centerView, int postion) {
        mCenterView = centerView;
        centerPostion = postion;
        notifyItemInserted(postion);
    }

    public View getCenterView() {
        return mCenterView;
    }

    public void setBottomView(View bottomView) {
        mBottomView = bottomView;
        notifyItemInserted(list.size() - 1);
    }

    public View getBottomView() {
        return mBottomView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null) {
            if (position == 0) return TYPE_HEADER;
        }
        if (mCenterView != null) {
            if (position == centerPostion) return TYPE_CENTER;
        }
        if (mBottomView != null) {
            if (position == listSize - 1) return TYPE_BOTTOM;
        }
        return TYPE_NORMAL;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        if (mHeaderView != null) {
            position--;
        }
        if (mCenterView != null) {
            if (position >= centerPostion) {
                position--;
            }
        } else if (mBottomView != null) {
            position--;
        }
        return position;
    }

    /**
     * 获取ListModel
     */
    public static <T> List<T> getConvertList(List<T> listData) {
        BaseListModel<T> list = new BaseListModel<>();
        list.addAll(listData);
        return list;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        onDestroy();
    }

    public void onDestroy() {
        mAdapterHelper.onDestroy();
    }

    public interface OnRecycleItemClickLinster {
        void onClick(int postion);
    }

    public interface OnRecycleItemClickLinster2 {
        void onClick(int postion);
    }

    public interface OnRecycleItemClickLinster3 {
        void onClick(int postion);
    }

    public void setOnRecycleItemClickLinster(OnRecycleItemClickLinster mOnRecycleItemClickLinster) {
        this.mOnRecycleItemClickLinster = mOnRecycleItemClickLinster;
    }

    public void setOnRecycleItem2ClickLinster(OnRecycleItemClickLinster2 mOnRecycleItemClickLinster2) {
        this.mOnRecycleItemClickLinster2 = mOnRecycleItemClickLinster2;
    }

    public void setOnRecycleItem3ClickLinster(OnRecycleItemClickLinster3 mOnRecycleItemClickLinster3) {
        this.mOnRecycleItemClickLinster3 = mOnRecycleItemClickLinster3;
    }
}
