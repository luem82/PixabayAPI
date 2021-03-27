package com.example.pixabayapi.datas.model

import java.nio.channels.spi.AbstractSelectionKey

data class Category(
    val title: String,
    val key: String,
    val thumbs: List<String>
)