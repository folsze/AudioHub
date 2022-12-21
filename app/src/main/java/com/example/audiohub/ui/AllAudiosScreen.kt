package com.example.audiohub.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.audiohub.AudioHubScreen
import com.example.audiohub.data.AllAudiosUiState
import com.example.audiohub.model.Audio
import com.example.audiohub.ui.components.AudioList

private const val TAG = "AllAudiosScreen"

@Composable
fun AllAudiosScreen(navController: NavHostController, allAudiosUiState: AllAudiosUiState) {
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
                text = "Rap Playlist",
            )
        }

//        val audioList: List<Audio> = Datasource().loadAllAudios()
        val audioList: List<Audio> = allAudiosUiState.audios
        Log.i(TAG, audioList.toString())

        AudioList(audioList)
        Button(
            onClick = {
                navController.navigate(AudioHubScreen.Album.name)
            },
        ) {
            Text("ALBUM!")
        }
    }
}
