package com.example.pixabayapi.datas.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TinyVideo(
    @SerializedName("height")
    val height: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
): Serializable