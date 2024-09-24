package com.example.sunnyweather.logic

import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import retrofit2.http.Query

// 定义Repository对象，用于处理与数据相关的操作
object Repository {
    // 使用liveData构建异步数据源，支持从IO线程进行网络请求
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            // 尝试调用网络层的搜索地点方法
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            // 检查响应状态是否为"ok"
            if (placeResponse.status == "ok") {
                val places = placeResponse.places // 获取地点列表
                Result.success(places) // 返回成功结果
            } else {
                // 如果状态不为"ok"，返回失败结果并抛出异常
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            // 捕获异常并返回失败结果
            Result.failure<List<Place>>(e)
        }
        emit(result) // 发射结果以供观察
    }
}
