package com.example.marvelcomposee.ui.screens.charactersDetail

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.marvelcomposee.MarvelApp
import com.example.marvelcomposee.R
import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.data.entities.Reference
import com.example.marvelcomposee.data.entities.Url
import com.example.marvelcomposee.data.repositories.CharacterRepository
import com.example.marvelcomposee.data.shared.ArrowBackIcon

@Composable
fun CharacterDetailScreen(id: Int,  onUpClick: () -> Unit) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit){
        characterState = CharacterRepository.findCharacter(characterId)
    }
    characterState?.let { c -> CharacterDetailScreen(it , unOpClick)}
}

@Composable
fun CharacterDetailScreen(character: Character , onUpClick: () -> Unit) {
    
    CharacterDetailScaffold(
        character = character,
        onUpClick = onUpClick
    ) {  padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ){
            item {
                Header(character)
            }
            section(Icons.Default.MoreVert, R.string.series, character.series)
            section(Icons.Default.List,  R.string.events, character.events)
            section(Icons.Default.List,  R.string.comic, character.comics)
            section(Icons.Default.List,  R.string.stories, character.stories)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.section(icon: ImageVector, @StringRes name: Int, items: List<Reference>) {
    if (items.isEmpty()) return
    item {
        Text(
            text = "Comics",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items){
       ListItem(
           headlineText = { Icon(imageVector = Icons.Default.List, contentDescription = null
       )})
    }
}

@Composable
fun Header(character: Character) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        Image(
            painter = rememberImagePainter(character.thumbnail),
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
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Url("type 1", "url 1"), Url("Comic 2", "url 2"))
    )
    MarvelApp {
        CharacterDetailScreen(character)
    }
}
