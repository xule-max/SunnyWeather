package com.example.sunnyweather.ui.place

import android.view.animation.Transformation
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunnyweather.logic.Repository
import com.example.sunnyweather.logic.model.Place

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>() // 存储搜索查询字符串
    val placeList = ArrayList<Place>() // 缓存城市数据

    // 使用MediatorLiveData来观察searchLiveData的变化并获取城市数据
    val placeLiveData = MediatorLiveData<Result<List<Place>>>()

    init {
        // 观察searchLiveData的变化
        placeLiveData.addSource(searchLiveData) { query ->
            query?.let {
                // 当query变化时，从仓库中获取城市数据
                placeLiveData.addSource(Repository.searchPlaces(it)) { result ->
                    placeLiveData.value = result // 更新placeLiveData的值
                }
            }
        }
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query // 更新searchLiveData的值
    }
}

