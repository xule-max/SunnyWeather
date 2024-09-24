package com.example.sunnyweather.logic.model

import android.location.Location
import com.google.gson.annotations.SerializedName

// 在logic/model包下定义数据模型，以支持天气预报应用的数据处理

// PlaceResponse类用于表示天气API返回的地点响应
data class PlaceResponse(
    val status: String, // 响应状态，表示请求的结果
    val places: List<Place> // 包含多个地点信息的列表
)

// Place类表示单个地点的信息
data class Place(
    val name: String, // 地点名称
    val location: Location, // 地点的地理位置
    @SerializedName("formatted_address") val address: String // 格式化的地址
)

// Location类表示地点的经纬度信息
data class Location(
    val lng: String, // 经度
    val lat: String // 纬度
)
