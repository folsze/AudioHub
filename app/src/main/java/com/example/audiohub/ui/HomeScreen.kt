package com.example.audiohub.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    onAddAudioButtonClicked: () -> Unit,
    onAddAlbumButtonClicked: () -> Unit,
    onViewAllAudiosButtonClicked: () -> Unit,
    atLeastOneAlbumExists: Boolean,
) {
    Column(
        Modifier
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "AudioHub",
            Modifier.padding(vertical = 40.dp),
            fontSize = 50.sp
        )

        if (atLeastOneAlbumExists) {
            Button(
                onClick = onAddAudioButtonClicked,
            ) {
                Text("Add audio")
            }
        }

        Button(
            onClick = onAddAlbumButtonClicked,
        ) {
            Text("Add album")
        }

        Button(
            onClick = onViewAllAudiosButtonClicked,
        ) {
            Text("View all audios")
        }
    }
}