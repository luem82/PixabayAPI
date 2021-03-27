package com.example.pixabayapi.datas.model


import com.example.pixabayapi.datas.model.PhotoDetail
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PhotoPageResult(
    @SerializedName("hits")
    val hits: List<PhotoDetail>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
): Serializable