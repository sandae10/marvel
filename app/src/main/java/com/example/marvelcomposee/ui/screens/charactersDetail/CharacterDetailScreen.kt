package com.example.marvelcomposee.ui.screens.charactersDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.marvelcomposee.MarvelApp
import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.data.repositories.CharacterRepository

@Composable
fun CharacterDetailScreen(id : Int) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit){
        characterState = CharacterRepository.findCharacter(id)
    }
    characterState?.let { c -> CharacterDetailScreen(c)}
}

@Composable
fun CharacterDetailScreen(character: Character) {
  LazyColumn(
      modifier = Modifier.fillMaxWidth()
  ){
          item {
              Header(character)
         }
     }
}

@Composable
fun Header(character: Character) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        Image(
            painter = rememberImagePainter(character),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp, 0.dp))
    }
}

@Preview(widthDp = 400, heightDp = 700)
@Composable
fun CharacterDetailScreenPreview() {
    val character = Character(
        1,
        "Iron Man",
        "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?",
        "",
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList()
    )
    MarvelApp {
        CharacterDetailScreen(character)
    }
}
