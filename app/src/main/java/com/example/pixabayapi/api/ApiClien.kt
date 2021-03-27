package com.example.pixabayapi.api

import com.example.pixabayapi.utils.Consts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClien {

    private var retrofit: Retrofit? = null

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Consts.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}