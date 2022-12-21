package com.example.audiohub.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiohub.data.AudiosAndAlbumsUiState
import com.example.audiohub.model.Audio
import com.example.audiohub.ui.components.AudioList

private const val TAG = "AllAudiosScreen"

@Composable
fun AllAudiosScreen(
    audiosAndAlbumsUiState: AudiosAndAlbumsUiState
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            Text(
                fontSize = 30.sp,
                text = "All Audios",
            )
        }

        val audioList: List<Audio> = audiosAndAlbumsUiState.audios
        Log.i(TAG, audioList.toString())

        AudioList(audioList)
    }
}
