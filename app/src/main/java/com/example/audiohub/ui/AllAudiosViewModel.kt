package com.example.audiohub.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.audiohub.R
import com.example.audiohub.data.AllAudiosUiState
import com.example.audiohub.data.Datasource
import com.example.audiohub.model.Audio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "CreateAudioViewModel"

class AllAudiosViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AllAudiosUiState())
    val uiState: StateFlow<AllAudiosUiState> = _uiState.asStateFlow()

    fun addAudio(audio: Audio) {
        _uiState.update { currentState ->
            currentState.copy(audios = newList(_uiState.value.audios, audio))
        }
    }

    fun resetAudios() {
        _uiState.value = AllAudiosUiState()
    }

    private fun newList(audios: MutableList<Audio>, audio: Audio): MutableList<Audio> {
        val newList = audios.toMutableList()
        newList.add(audio)
        return newList
    }

}
