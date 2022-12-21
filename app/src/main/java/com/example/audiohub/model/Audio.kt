package com.example.audiohub.model

import androidx.annotation.DrawableRes

data class Audio (
    var id: Int,
    var title: String,
    var artistName: String,
    var album: Album,
)