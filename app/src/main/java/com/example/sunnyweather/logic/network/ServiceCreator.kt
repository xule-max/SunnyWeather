package com.example.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// 在logic/network包下创建一个Retrofit构建器的单例类

object ServiceCreator {
    // 定义基础URL，指向彩云天气API
    private const val BASE_URL = "https://api.caiyunapp.com/"

    // 使用Retrofit.Builder构建Retrofit实例
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // 设置基础URL
        .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器，用于处理JSON数据
        .build()

    // 创建指定服务类的实例
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    // 支持使用Kotlin的reified类型参数来创建服务实例
    inline fun <reified T> create(): T = create(T::class.java)
}

