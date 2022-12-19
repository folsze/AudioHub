package com.example.audiohub.model

import androidx.annotation.DrawableRes

data class Audio (
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    val title: String,
    val artistName: String,
    val albumName: String,
)