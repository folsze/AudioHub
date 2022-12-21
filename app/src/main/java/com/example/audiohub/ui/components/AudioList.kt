package com.example.audiohub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiohub.model.Audio

@Composable
fun AudioList(audioList: List<Audio>) {
    LazyColumn {
        items(audioList) {
                audio -> AudioRow(audio)
        }
    }
}

@Composable
fun AudioRow(audio: Audio) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) { // multiple songs
            Row () { // left side wrapper
                Column() {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(id = audio.album.coverImageResourceId),
                        contentDescription = null
                    )
                }
                Column(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Row() {
                        Text(audio.title)
                    }
                    Row() {
                        Text(
                            audio.artistName,
                            fontSize = 14.sp
                        )
                    }
                }
            }
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { }
                ) {
                    Icon(Icons.Filled.MoreVert, contentDescription = null)
                }
            }
        }
    }
}
