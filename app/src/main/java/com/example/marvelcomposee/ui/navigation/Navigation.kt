package com.example.marvelcomposee.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.marvelcomposee.ui.screens.caracters.CharactersScreen
import com.example.marvelcomposee.ui.screens.charactersDetail.CharacterDetailScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ){
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
    }
}

private fun NavGraphBuilder.characterNav(
    navController: NavController
){

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
            val id = it.findArg<Int>(NavArg.ItemId)
            CharacterDetailScreen(
                characterId = id,
                onUpClick = {navController.popBackStack()}
            ) {
            }
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