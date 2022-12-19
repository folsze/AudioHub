package com.example.audiohub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiohub.data.Datasource
import com.example.audiohub.model.Audio
import com.example.audiohub.ui.theme.AudioHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AudioHubApp()
                }
            }
        }
    }
}

@Composable
fun AudioHubApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Audio Hub")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 10.dp
            )
        }, content = {
            Album()
        }
    )
}

@Composable
fun Album() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .size(400.dp)
                .padding(top = 60.dp, bottom = 60.dp)
            ,
            painter = painterResource(id = R.drawable.album4),
            contentDescription = null
        )
        val datasource = Datasource().loadAlbum1Songs()
        AudioList(datasource)
    }
}

@Composable
fun Playlist(

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
                text = "Rap Playlist",
            )
        }

        val audioList: List<Audio> = Datasource().loadAllAudios()
        AudioList(audioList)
    }
}

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
                        painter = painterResource(id = audio.imageResourceId),
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

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun TableScreen() {
    // Just a fake data... a Pair of Int and String
    val tableData = (1..100).mapIndexed { index, item ->
        index to "Item $index"
    }
    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        // Here is the header
        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Column 1", weight = column1Weight)
                TableCell(text = "Column 2", weight = column2Weight)
            }
        }
        // Here are all the lines of your table.
        items(tableData) {
            val (id, text) = it
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = id.toString(), weight = column1Weight)
                TableCell(text = text, weight = column2Weight)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AudioHubTheme {
        Album()
    }
}