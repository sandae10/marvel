package com.example.marvelcomposee.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelcomposee.R
import com.example.marvelcomposee.ui.navigation.NavItem
import com.example.marvelcomposee.ui.navigation.Navigation
import com.example.marvelcomposee.ui.screens.charactersDetail.AppBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?: ""
    val showUpNavigation = currentRoute !in NavItem.values().map { it.navCommand.route }
    MarvelScreen {
        Scaffold(
            topBar ={
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_marvel)) },
                    navigationIcon = {
                        if (showUpNavigation) {
                            run {
                                AppBarIcon(
                                    imageVector = Icons.Default.ArrowBack,
                                    onClick = { navController.popBackStack() })
                            }
                        } else null
                    }
                )
            },
            bottomBar = {
                BottomAppBar {
                    NavItem.values().forEach { item ->
                        val title = stringResource(item.title)

                    }
                }

            },
            drawerContent = {

            }
        ) {padding ->
            Box(modifier = Modifier.padding(padding)){
                Navigation(navController)
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