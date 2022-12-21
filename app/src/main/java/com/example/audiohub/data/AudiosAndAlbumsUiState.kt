package com.example.audiohub.data

import com.example.audiohub.model.Album
import com.example.audiohub.model.Audio

data class AudiosAndAlbumsUiState (
    val audios: MutableList<Audio> = mutableListOf(),
    val albums: MutableList<Album> = mutableListOf()
)