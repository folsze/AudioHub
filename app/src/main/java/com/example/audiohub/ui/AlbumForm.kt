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
import com.example.audiohub.model.Album

private const val TAG = "AlbumFormScreen"

@Composable
fun AlbumFormScreen(
    onAddAlbum: (album: Album) -> Unit
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Enter Album Details",
            fontSize = 40.sp
        )
        FormFields(
            onAddAlbum
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormFields(
    onAddAlbum: (album: Album) -> Unit
) {
    var albumName by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = albumName,
        onValueChange = { newText ->
            albumName = newText
        },
        label = {
            Text("Album name")
        }
    )


    val listItems = listOf(
        Album("Album 1", R.drawable.album1),
        Album("Album 2", R.drawable.album2),
        Album("Album 3", R.drawable.album3),
        Album("Album 4", R.drawable.album4),
    )

    var selectedAlbumCover by remember {
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
            value = selectedAlbumCover.name,
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
                    selectedAlbumCover = selectedOption
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
            onAddAlbum(Album(albumName.text, selectedAlbumCover.coverImageResourceId))
            Log.i(TAG, "ADDED")
        }
    ) {
        Text("Add to all albums")
    }
}
