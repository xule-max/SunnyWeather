package com.example.sunnyweather.ui.place

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunnyweather.R
import com.example.sunnyweather.databinding.FragmentPlaceBinding

class PlaceFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private lateinit var adapter: PlaceAdapter
    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 初始化 ViewBinding
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 设置 RecyclerView 的布局管理器
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager

        // 创建 Adapter 并设置到 RecyclerView
        adapter = PlaceAdapter(this, viewModel.placeList)
        binding.recyclerView.adapter = adapter

        binding.searchPlaceEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val content = s.toString()
                if (content.isNotEmpty()) {
                    viewModel.searchPlaces(content) // 搜索城市
                } else {
                    // 如果内容为空，隐藏 RecyclerView，显示背景图片
                    binding.recyclerView.visibility = View.GONE
                    binding.bgImageView.visibility = View.VISIBLE
                    viewModel.placeList.clear() // 清空列表
                    adapter.notifyDataSetChanged() // 通知适配器数据已更改
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        // 观察 ViewModel 的 LiveData
        viewModel.placeLiveData.observe(viewLifecycleOwner, { result ->
            val places = result.getOrNull()
            if (places != null) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged() // 更新适配器
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 清除绑定，避免内存泄漏
    }
}
