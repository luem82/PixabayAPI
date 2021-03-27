package com.example.pixabayapi.datas.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoPageResult(
    @SerializedName("hits")
    val hits: List<VideoDetail>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
): Serializable