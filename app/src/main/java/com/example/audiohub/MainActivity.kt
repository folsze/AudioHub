package com.example.audiohub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.audiohub.ui.theme.AudioHubTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.audiohub.ui.*

private const val TAG = "MainActivity"

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

enum class AudioHubScreen(@StringRes val title: Int) {
    Home(title = R.string.home),
    AllAudios(title = R.string.allAudios),
    Album(title = R.string.album),
    AudioForm(title= R.string.audioForm)
}

@Composable
fun AudioHubApp(viewModel: AllAudiosViewModel = viewModel()) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AudioHubScreen.valueOf(
        backStackEntry?.destination?.route ?: AudioHubScreen.AllAudios.name
    )

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
        }
    ) {
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            modifier = Modifier.padding(),
            navController = navController,
            startDestination = AudioHubScreen.Home.name,
        ) {
            composable(route = AudioHubScreen.Home.name) {
                HomeScreen(
                    onAddAudioButtonClicked = {
                        navController.navigate(AudioHubScreen.AudioForm.name)
                    },
                    onViewAllAudiosButtonClicked = {
                        navController.navigate(AudioHubScreen.AllAudios.name)
                    },
                    // todo later: can navigate to specific album
                    // todo even later: can search for albums (and in that screen: select from results)
                )
            }
            composable(route = AudioHubScreen.AudioForm.name) {
                AudioFormScreen (
                    navController = navController,
                    onAddAudio = { viewModel.addAudio(it) }
                )
            }
            composable(route = AudioHubScreen.AllAudios.name) {
                AllAudiosScreen(
                    navController,
                    allAudiosUiState = uiState
                )
            }
            composable(route = AudioHubScreen.Album.name) {
                AlbumScreen(

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AudioHubTheme {
        AlbumScreen()
    }
}