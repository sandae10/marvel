package com.example.marvelcomposee.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.marvelcomposee.R
import com.example.marvelcomposee.ui.MarvelAppState
import com.example.marvelcomposee.ui.navigation.DrawerContent
import com.example.marvelcomposee.ui.navigation.Navigation
import com.example.marvelcomposee.ui.rememberMarvelAppState
import com.example.marvelcomposee.ui.screens.charactersDetail.AppBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelApp() {
    val appState = rememberMarvelAppState()
    MarvelScreen {
        Scaffold(
            topBar ={
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_marvel)) },
                    navigationIcon = {
                       if (appState.showUpNavigation) {
                           run {
                               AppBarIcon(
                                   imageVector = Icons.Default.ArrowBack,
                                   onClick = { appState.onUpClick() })
                           }.run {
                               AppBarIcon(
                                   imageVector = Icons.Default.Menu,
                                   onClick = { appState.onMenuClick() }
                               )
                           }
                        }
                    }
                )
            },
            bottomBar = {
                if (appState.showUpNavigation)
                AppBottomNavigation(
                            bottomNavOptions = MarvelAppState.BOTTOM_NAV_OPTIONS,
                            currentRoute = appState.currentRoute,
                            onNavItemClick = {
                    appState.onNavItemClick(it) })
            },
            drawerContent = {
                DrawerContent(
                drawerOptions = MarvelAppState.DRAWER_OPTIONS,
                selectedIndex =  appState.drawerSelectedIndex,
                onOptionClick = { appState.onDrawerOptionClick(it)

                }
            ) },
            scaffoldState = appState.scaffoldState
        ) {padding ->
            Box(modifier = Modifier.padding(padding)){
                Navigation(appState.navController)
            }
        }
    }
}

@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    MarvelComposeeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}