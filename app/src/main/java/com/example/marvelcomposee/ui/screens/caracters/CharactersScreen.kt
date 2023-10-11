package com.example.marvelcomposee.ui.screens.caracters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.data.repositories.CharacterRepository

@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by remember { mutableStateOf(emptyList<Character>()) }

    LaunchedEffect(Unit){
        charactersState = CharacterRepository.getCharacters()
    }
    CharactersScreen(
        characterData = charactersState,
        onClick = onClick
    )
}

@Composable
fun CharactersScreen(characterData: List<Character>, onClick: (Character) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ){
        items(characterData) {
            CharacterItem(
                character = it,
                modifier = Modifier.clickable { onClick(it) }
            )
        }
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
             contentDescription = character.name,
             contentScale = ContentScale.Crop,
             modifier = Modifier
                 .background(Color.LightGray)
                 .fillMaxWidth()
                 .aspectRatio(1f)
         )}
        Text(
            text = character.name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}

