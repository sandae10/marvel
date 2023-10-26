package com.example.marvelcomposee.ui.screens.charactersDetail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.marvelcomposee.data.entities.MarvelItem
import com.example.marvelcomposee.ui.screens.characters.Header
import com.example.marvelcomposee.ui.screens.characters.section

@Composable
fun MarvelItemDetailScreen(loading : Boolean = false, marvelItem: MarvelItem) {
    if (loading) {
        CircularProgressIndicator()
    }

    if (marvelItem != null) {
    MarvelItemDetailScaffold(
        marvelItem = marvelItem
    ){ padding ->
       LazyColumn(
           modifier = Modifier
               .fillMaxWidth()
               .padding(padding))
        {
            item { 
                Header(marvelItem = marvelItem)
            }
            marvelItem.references.forEach {
                val (icon, @StringRes stringRes) = it.type.createUiData()
                section(icon, stringRes, it.references)
            }
             }
         }
    }
}



