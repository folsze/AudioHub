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
    onAddAudioButtonClicked:  () -> Unit,
    onViewAllAudiosButtonClicked: () -> Unit,
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

        Button(
            onClick = onAddAudioButtonClicked,
        ) {
            Text("Add Audio")
        }

        Button(
            onClick = onViewAllAudiosButtonClicked,
        ) {
            Text("Add Audio")
        }
    }
}