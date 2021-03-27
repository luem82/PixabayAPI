package com.example.pixabayapi.datas.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoDetail(
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("favorites")
    val favorites: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("pageURL")
    val pageURL: String,
    @SerializedName("picture_id")
    val pictureId: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("userImageURL")
    val userImageURL: String,
    @SerializedName("videos")
    val videos: Videos,
    @SerializedName("views")
    val views: Int
):Serializable