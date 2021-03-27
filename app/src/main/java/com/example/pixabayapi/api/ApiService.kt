package com.example.pixabayapi.api

import com.example.pixabayapi.datas.model.PhotoPageResult
import com.example.pixabayapi.datas.model.VideoPageResult
import com.example.pixabayapi.utils.Consts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //https://pixabay.com/api/?key=17400316-ab031a1f11b1982232b6cba56&image_type=illustration&pretty=true
    @GET("/api/")
    fun getPhotos(
        @Query("lang") lang: String = Consts.LANG,
        @Query("key") key: String = Consts.API_KEY,
        @Query("pretty") pretty: Boolean = true,
        @Query("image_type") image_type: String,
        @Query("per_page") per_page: Int
    ): Call<PhotoPageResult>


    @GET("/api/")
    fun searchPhotos(
        @Query("q") query: String,
        @Query("lang") lang: String = Consts.LANG,
        @Query("key") key: String = Consts.API_KEY,
        @Query("pretty") pretty: Boolean = true,
        @Query("image_type") image_type: String,
        @Query("per_page") per_page: Int
    ): Call<PhotoPageResult>

    //https://pixabay.com/api/videos/?key=17400316-ab031a1f11b1982232b6cba56&pretty=true&video_type=film
    @GET("/api/videos/")
    fun getVideos(
        @Query("lang") lang: String = Consts.LANG,
        @Query("key") key: String = Consts.API_KEY,
        @Query("pretty") pretty: Boolean = true,
        @Query("video_type") video_type: String,
        @Query("per_page") per_page: Int
    ): Call<VideoPageResult>

    @GET("/api/videos/")
    fun searchVideos(
        @Query("q") query: String,
        @Query("lang") lang: String = Consts.LANG,
        @Query("key") key: String = Consts.API_KEY,
        @Query("pretty") pretty: Boolean = true,
        @Query("video_type") video_type: String,
        @Query("per_page") per_page: Int
    ): Call<VideoPageResult>
}