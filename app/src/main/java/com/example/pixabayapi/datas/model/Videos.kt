package com.example.pixabayapi.datas.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Videos(
    @SerializedName("large")
    val large: LargeVideo,
    @SerializedName("medium")
    val medium: MediumVideo,
    @SerializedName("small")
    val small: SmallVideo,
    @SerializedName("tiny")
    val tiny: TinyVideo
): Serializable