package com.example.besediffutil

import android.content.Context
import android.view.View
import android.widget.RadioButton
import com.example.gbasediffutil.base.BaseRecycleAdapter
import com.example.gbasediffutil.base.BaseViewHolder

/**
 * author：G
 * time：2021/1/20 10:40
 * about：
 */
class MainAdapter(context: Context) : BaseRecycleAdapter<MainBean>(context) {
    override fun getLayoutId(): Int {
        return R.layout.main_list_item
    }

    override fun setDataToHolder(holder: BaseViewHolder, t: MainBean, postion: Int) {
        val radio = holder.getView<RadioButton>(R.id.radio)
        holder.setText(R.id.name, "name=${t.name} id=${t.id}  postion=$postion")
        radio.isChecked = t.isCheck
        holder.setClick(View.OnClickListener { mOnRecycleItemClickLinster.onClick(postion) },R.id.rootView)
    }
}