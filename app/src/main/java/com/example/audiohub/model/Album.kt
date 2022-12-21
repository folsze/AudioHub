package com.example.audiohub.model

import androidx.annotation.DrawableRes

data class Album(
    val name: String,
    @DrawableRes val coverImageResourceId: Int
)
