package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

// 定义SunnyWeatherApplication类，继承自Application，用于全局应用配置
class SunnyWeatherApplication : Application() {
    companion object {
        // 定义一个常量TOKEN，用于存储天气API的访问令牌
        const val TOKEN = "qKhJsG6XmmM5FVJp"

        // 使用@SupressLint注解避免静态字段泄漏警告
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context // 声明一个全局Context变量
    }

    // 应用程序创建时调用，初始化全局Context
    override fun onCreate() {
        super.onCreate()
        // 设置全局Context为应用程序上下文
        context = applicationContext
    }
}
