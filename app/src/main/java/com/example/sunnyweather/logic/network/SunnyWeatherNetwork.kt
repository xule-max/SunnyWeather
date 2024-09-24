package com.example.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

// 定义SunnyWeatherNetwork对象，用于处理与网络相关的操作
object SunnyWeatherNetwork {
    // 创建PlaceService实例，以便进行API调用
    private val placeService = ServiceCreator.create<PlaceService>()

    // 使用挂起函数searchPlaces进行城市搜索，返回搜索结果
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    // 扩展函数，用于将Call<T>转换为挂起函数
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            // 异步执行API调用
            enqueue(object : Callback<T> {
                // 成功响应处理
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        // 如果响应体不为null，恢复协程
                        continuation.resume(body)
                    } else {
                        // 如果响应体为null，抛出异常
                        continuation.resumeWithException(
                            RuntimeException("response body is null")
                        )
                    }
                }

                // 失败响应处理
                override fun onFailure(call: Call<T>, t: Throwable) {
                    // 恢复协程并抛出异常
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}
