package com.example.besediffutil

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbasediffutil.base.BaseRecycleAdapter
import com.example.gbasediffutil.base.DiffCallBack
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * author：G
 * time：2021/1/20 9:32
 * about：basediffutil 使用示例
 **/
class MainActivity : AppCompatActivity() {

    private var mDataList: MutableList<MainBean> = ArrayList()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(this)
        lifecycle.addObserver(mainAdapter)//注册lifecycle监听生命周期
        mRecyclerView.adapter = mainAdapter
        initList()
        //单击
        mainAdapter.setOnRecycleItemClickLinster {
            mDataList[it].isCheck = !mDataList[it].isCheck
            mainAdapter.notifyItemChanged(it)
        }
    }

    /**
     * 模拟数据请求
     **/
    private fun initList() {
        mDataList.add(MainBean("测试----", mDataList.size - 1, true))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList.add(MainBean("测试----", mDataList.size - 1, false))
        mDataList = BaseRecycleAdapter.getConvertList(mDataList)
        mainAdapter.setList(mDataList, object : DiffCallBack<MainBean>() {
            override fun areItemsTheSame(oldItem: MainBean?, newItem: MainBean?): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MainBean?, newItem: MainBean?): Boolean {
                return oldItem?.isCheck == newItem?.isCheck
            }
        })
    }

    /**
     * 添加一条
     **/
    fun addItem(view: View) {
        //计算出一个随机的计算位置。
        val nextInt = Random().nextInt(mDataList.size)
        //添加进去
        mDataList.add(nextInt, MainBean("测试添加----", mDataList.size + 1, false))
    }

    /**
     * 删除最后一条
     **/
    fun delItem(view: View) {
        if (mDataList.size == 0) {
            Toast.makeText(this, "没有更多了", Toast.LENGTH_SHORT).show()
        }
        mDataList.removeAt(mDataList.size - 1)
    }

    /**
     * 模拟下拉刷新数据
     **/
    fun upadtaList(view: View) {
        val mNewList: MutableList<MainBean> = ArrayList()
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mNewList.add(MainBean("测试新数据----", mNewList.size + 100, false))
        mDataList = BaseRecycleAdapter.getConvertList(mNewList)
        mainAdapter.updataListData(mDataList)
    }
}