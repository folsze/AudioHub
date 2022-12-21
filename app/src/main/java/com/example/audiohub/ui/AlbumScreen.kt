package com.example.audiohub.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiohub.R
import com.example.audiohub.model.Audio
import com.example.audiohub.ui.components.AudioList

@Composable
fun AlbumScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .size(300.dp)
                .padding(top = 60.dp, bottom = 60.dp)
            ,
            painter = painterResource(id = R.drawable.album4),
            contentDescription = null
        )
        Text(
            text = "Album",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 40.sp,
        )

//        val datasource = Datasource().loadAlbum1Songs()
        val datasource = mutableListOf<Audio>() // FIXME: ERRORS!
        AudioList(datasource)
    }
}
