package com.example.audiohub.data

import com.example.audiohub.model.Audio

data class AllAudiosUiState (
    val audios: MutableList<Audio> = mutableListOf()
)