package com.example.marvelcomposee.ui.screens.characters

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.example.marvelcomposee.R
import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.ui.screens.charactersDetail.AppBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScaffold(
    character: Character,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit)
{
    val context = LocalContext.current
    Scaffold(

        floatingActionButton = {
            if(character.urls.isEmpty()) {
            FloatingActionButton(onClick = { shareCharacter(context , character) }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(id = R.string.share_character)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(

            ) {
                AppBarIcon(imageVector = Icons.Default.Menu, onClick = { /*TODO*/ })
                Spacer(modifier = Modifier.weight(1f))
                AppBarIcon(imageVector = Icons.Default.Favorite, onClick = { /*TODO*/ })
            }
        },
        content = content

    )
}

fun shareCharacter(context : Context, character: Character){
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(character.name)
        .setText(character.urls.first().url)
        .intent
        .also(context::startActivity)
}