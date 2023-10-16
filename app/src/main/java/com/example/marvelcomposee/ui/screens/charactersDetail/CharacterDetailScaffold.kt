package com.example.marvelcomposee.ui.screens.charactersDetail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.example.marvelcomposee.R
import com.example.marvelcomposee.data.entities.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScaffold(
    character: Character,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit)
{
    val extent = LocalContext.current
    Scaffold(
        topBar ={
            TopAppBar(
                title = { Text(character.name) },
                navigationIcon = { (onUpClick)} ,
                actions = {
                    AppBarOverflowMenu(urls = character.urls) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { shareCharacter(context =  , character) }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(id = R.string.share_character
                    )
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = content
    )
}

fun shareCharacter(context : Context, character: Character){
    val intent = ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(character.name)
        .setText(character.urls.first().url)
        .intent
      context.startActivity(intent)
}