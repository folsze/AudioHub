package com.example.audiohub.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiohub.R
import com.example.audiohub.model.Audio
import com.example.audiohub.model.Album

private const val TAG = "AudioFormScreen"

@Composable
fun AudioFormScreen(
    onAddAudio: (audio: Audio) -> Unit,
    allAvailableAlbums: MutableList<Album>
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Enter Song Details",
            fontSize = 40.sp
        )
        AudioFormFields(
            onAddAudio,
            allAvailableAlbums
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AudioFormFields(
    onAddAudio: (audio: Audio) -> Unit,
    allAvailableAlbums: MutableList<Album>
) {
    var songName by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = songName,
        onValueChange = { newText ->
            songName = newText
        },
        label = {
            Text("Song name")
        }
    )

    var artistName by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = artistName,
        onValueChange = { newText ->
            artistName = newText
        },
        label = {
            Text("Artist name")
        }
    )

    Button(
        onClick = {
            onAddAudio(Audio(songName.text, artistName.text, Album("yo", R.drawable.album1)))
            Log.i(TAG, "ADDED")
        }
    ) {
        Text("Add to all audios")
    }
}
