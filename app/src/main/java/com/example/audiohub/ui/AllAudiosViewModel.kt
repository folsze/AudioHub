package com.example.audiohub.ui

import androidx.lifecycle.ViewModel
import com.example.audiohub.data.AudiosAndAlbumsUiState
import com.example.audiohub.model.Album
import com.example.audiohub.model.Audio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "CreateAudioViewModel"

class AllAudiosViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AudiosAndAlbumsUiState())
    val uiState: StateFlow<AudiosAndAlbumsUiState> = _uiState.asStateFlow()

    fun addAudio(audio: Audio) {
        _uiState.update { currentState ->
            currentState.copy(audios = newAudioList(_uiState.value.audios, audio))
        }
    }

    private fun newAudioList(audios: MutableList<Audio>, audio: Audio): MutableList<Audio> {
        val newList = audios.toMutableList()
        newList.add(audio)
        return newList
    }
    
    fun resetAudios() {
        _uiState.value = AudiosAndAlbumsUiState()
    }

    fun addAlbum(album: Album) {
        _uiState.update { currentState ->
            currentState.copy(albums = newAlbumList(_uiState.value.albums, album))
        }
    }

    private fun newAlbumList(albums: MutableList<Album>, album: Album): MutableList<Album> {
        val newList = albums.toMutableList()
        newList.add(album)
        return newList
    }
}
