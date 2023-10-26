package com.example.marvelcomposee.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.marvelcomposee.ui.screens.characters.CharacterDetailScreen
import com.example.marvelcomposee.ui.screens.characters.CharactersScreen
import com.example.marvelcomposee.ui.screens.comics.ComicDetailScreen
import com.example.marvelcomposee.ui.screens.comics.ComicsScreen
import com.example.marvelcomposee.ui.screens.events.EventDetailScreen
import com.example.marvelcomposee.ui.screens.events.EventScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ){

        composable(NavCommand.ContentType(Feature.SETTINGS)){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            Text("Settings", style = MaterialTheme.typography.bodyMedium)
            
        }
    }
}

private fun NavGraphBuilder.characterNav(navController: NavController){
    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route ,
        route = Feature.CHARACTERS.route
    ){
        composable(NavCommand.ContentType(Feature.CHARACTERS)){
            CharactersScreen(
                onClick = {character ->
                    navController.navigate(
                        NavCommand.ContentDetail(Feature.CHARACTERS).createRoute(character.id))
                }
            )
        }
        composable(NavCommand.ContentDetail(Feature.CHARACTERS)){
            CharacterDetailScreen()
        }
    }
}

private fun NavGraphBuilder.comicsNav(navController: NavController){
    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            ComicsScreen(
                onClick = {comic ->
                    navController.navigate(
                        NavCommand.ContentDetail(Feature.COMICS).createRoute(comic.id)
                    )
                }
            )
        }
    }
    composable(NavCommand.ContentDetail(Feature.COMICS)) {
        ComicDetailScreen()
    }
}
private fun NavGraphBuilder.eventsNav(navController: NavController){
    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ){
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            EventScreen(
                onClick = {event ->
                    navController.navigate(
                        NavCommand.ContentDetail(Feature.EVENTS).createRoute(event.id)
                    )
                }
            )
        }
        composable(NavCommand.ContentDetail(Feature.EVENTS)) {
            EventDetailScreen()
        }
    }
}
private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}
private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}