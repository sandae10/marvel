package com.example.marvelcomposee.ui.screens.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.ui.screens.charactersDetail.MarvelItemDetailScreen
import com.example.marvelcomposee.ui.screens.charactersDetail.MarvelItemsListScreen

@Composable
fun CharactersScreen(onClick: (Character) -> Unit, viewModel: CharactersViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemsListScreen(
        loading = state.loading,
        items = state.characters,
        onClick = onClick
    )
}

@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = viewModel() ) {
    val {
        MarvelItemDetailScreen(
            loading = viewModel.state.loading,
            marvelItem = it
        )
    }
}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
     Card {
         Image(
             painter = rememberImagePainter(character.thumbnail),
             contentDescription = character.description,
             contentScale = ContentScale.Crop,
             modifier = Modifier
                 .background(Color.LightGray)
                 .fillMaxWidth()
                 .aspectRatio(1f)
         )}
        Text(
            text = character.description,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}

