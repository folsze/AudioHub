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
import androidx.compose.ui.res.stringResource
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
    AudioForm(title = R.string.audioForm),
    AlbumForm(title = R.string.albumForm)
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
            AudioHubAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
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
                    atLeastOneAlbumExists = uiState.albums.size > 0,
                    onAddAudioButtonClicked = {
                        navController.navigate(AudioHubScreen.AudioForm.name)
                    },
                    onAddAlbumButtonClicked = {
                        navController.navigate(AudioHubScreen.AlbumForm.name)
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
                    onAddAudio = { viewModel.addAudio(it) },
                    allAvailableAlbums = uiState.albums
                )
            }
            composable(route = AudioHubScreen.AllAudios.name) {
                AllAudiosScreen(
                    audiosAndAlbumsUiState = uiState
                )
            }
            composable(route = AudioHubScreen.Album.name) {
                AlbumScreen(

                )
            }
            composable(route = AudioHubScreen.AlbumForm.name) {
                AlbumFormScreen(
                    onAddAlbum = { viewModel.addAlbum(it) }
                )
            }
        }
    }
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun AudioHubAppBar(
    currentScreen: AudioHubScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 50.dp
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AudioHubTheme {
        AlbumScreen()
    }
}