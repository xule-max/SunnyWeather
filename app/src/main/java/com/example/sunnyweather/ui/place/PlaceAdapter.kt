package com.example.sunnyweather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.R
import com.example.sunnyweather.logic.model.Place

class PlaceAdapter(private val fragment: Fragment, private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    // ViewHolder类用于缓存视图中的组件
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 获取布局中的TextView组件
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeAddress: TextView = view.findViewById(R.id.placeAddress)
    }

    // 创建ViewHolder并初始化视图
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 从XML布局中加载视图
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        return ViewHolder(view) // 返回新的ViewHolder实例
    }

    // 绑定数据到ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position] // 获取当前位置的Place对象
        holder.placeName.text = place.name // 设置Place名称
        holder.placeAddress.text = place.address // 设置Place地址
    }

    // 返回数据集的大小
    override fun getItemCount() = placeList.size
}
