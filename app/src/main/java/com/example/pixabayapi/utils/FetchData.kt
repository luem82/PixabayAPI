package com.example.pixabayapi.utils

import android.util.Log
import com.example.pixabayapi.api.ApiClien
import com.example.pixabayapi.api.ApiService
import com.example.pixabayapi.datas.model.Category
import com.example.pixabayapi.datas.model.PhotoDetail
import com.example.pixabayapi.datas.model.VideoDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object FetchData {

    /*suspend fun Categories() {
        withContext(Dispatchers.IO) {
            for (i: Int in 0..19) {
                var cate = Consts.LIST_CATE_KEY[i].substringBefore("/")
                var api = ApiClien.retrofitInstance!!.create(ApiService::class.java)
                    .searchPhotos(query = cate, image_type = Consts.PHOTO_TYPE_PHOTO, per_page = 4)
                var mmm = api!!.execute().body()!!.hits
                var a = mmm.get(0).previewURL
                var b = mmm.get(1).previewURL
                var c = mmm.get(2).previewURL
                var d = mmm.get(3).previewURL
                var vvv = StringBuilder()
                vvv.append('"' + a + '"' + ',' + '"' + b + '"' + ',' + '"' + c + '"' + ',' + '"' + d + '"' + ',')
                Log.e("ppp", "${i} to listOf(${vvv})")
            }
        }
    }*/

    fun getCategories(): MutableList<Category> {
        val result = mutableListOf<Category>()
        for (i: Int in 0..19) {
            result.add(
                Category(
                    Consts.LIST_CATE_KEY[i].substringAfter("/"),
                    Consts.LIST_CATE_KEY[i].substringBefore("/"),
                    Consts.LIST_CATE_THUMB[i]!!
                )
            )
        }
        Collections.shuffle(result)
        return result
    }

    suspend fun searchPhotos(
        imageType: String, count: Int, query: String
    ): MutableList<PhotoDetail> {
        var result = mutableListOf<PhotoDetail>()
        withContext(Dispatchers.IO) {
            var api = ApiClien.retrofitInstance!!.create(ApiService::class.java)
                .searchPhotos(query = query, image_type = imageType, per_page = count)
            result.addAll(api.execute().body()!!.hits)
        }
        return result
    }

    suspend fun searchVideos(
        videoType: String, count: Int, query: String
    ): MutableList<VideoDetail> {
        var result = mutableListOf<VideoDetail>()
        withContext(Dispatchers.IO) {
            var api = ApiClien.retrofitInstance!!.create(ApiService::class.java)
                .searchVideos(query = query, video_type = videoType, per_page = count)
            result.addAll(api.execute().body()!!.hits)
        }
        return result
    }

    suspend fun fetchFirstPhotos(imageType: String, count: Int): MutableList<PhotoDetail> {
        var result = mutableListOf<PhotoDetail>()
        withContext(Dispatchers.IO) {
            var api = ApiClien.retrofitInstance!!.create(ApiService::class.java)
                .getPhotos(image_type = imageType, per_page = count)
            result.addAll(api.execute().body()!!.hits)
        }
        return result
    }

    suspend fun fetchFirstVideos(videoType: String, count: Int): MutableList<VideoDetail> {
        var result = mutableListOf<VideoDetail>()
        withContext(Dispatchers.IO) {
            var api = ApiClien.retrofitInstance!!.create(ApiService::class.java)
                .getVideos(video_type = videoType, per_page = count)
            result.addAll(api.execute().body()!!.hits)
        }
        return result
    }
}