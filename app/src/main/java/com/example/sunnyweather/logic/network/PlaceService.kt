package com.example.sunnyweather.logic.network

import com.example.sunnyweather.SunnyWeatherApplication
import com.example.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// 在logic/network包下定义用于访问彩云天气城市搜索API的Retrofit接口

interface PlaceService {
    // 使用GET请求访问城市搜索API，传入TOKEN和语言参数
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse> // 定义搜索地点的方法，接受查询参数
}
