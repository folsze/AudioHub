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
import androidx.navigation.NavController
import com.example.audiohub.AudioHubScreen
import com.example.audiohub.model.Audio
import com.example.audiohub.R
import com.example.audiohub.model.Album

private const val TAG = "AudioFormScreen"

@Composable
fun AudioFormScreen(
    navController: NavController,
    onAddAudio: (audio: Audio) -> Unit
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Enter Song Details",
            fontSize = 40.sp
        )
        FormFields(
            navController,
            onAddAudio
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormFields(
    navController: NavController,
    onAddAudio: (audio: Audio) -> Unit
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


    val listItems = listOf(
        Album("Album 1", R.drawable.album1),
        Album("Album 2", R.drawable.album2),
        Album("Album 3", R.drawable.album3),
        Album("Album 4", R.drawable.album4),
    )

    var selectedAlbum by remember {
        mutableStateOf(listItems[0])
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    // the box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // text field
        TextField(
            value = selectedAlbum.name,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedAlbum = selectedOption
                    expanded = false
                }) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = selectedOption.name)
                        Image(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(id = selectedOption.coverImageResourceId),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }


    Button(
        onClick = {
            onAddAudio(Audio(1, songName.text, artistName.text, selectedAlbum))
            Log.i(TAG, "ADDED")
        }
    ) {
        Text("Add to all audios")
    }

    Button(
        onClick = {
            navController.navigate(AudioHubScreen.AllAudios.name)
        }
    ) {
        Text("View all audios")
    }
}
