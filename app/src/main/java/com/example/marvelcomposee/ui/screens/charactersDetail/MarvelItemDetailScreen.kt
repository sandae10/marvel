package com.example.marvelcomposee.ui.screens.charactersDetail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.marvelcomposee.data.entities.MarvelItem

@Composable
fun MarvelItemDetailScreen(
    marvelItem: MarvelItem,
    onUpClick: () -> Unit
) {
    MarvelItemDetailScaffold(
        marvelItem = marvelItem,
        onUpClick= onUpClick
    ){ padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ){
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