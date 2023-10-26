package com.example.marvelcomposee.ui.screens.charactersDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvelcomposee.R
import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.data.entities.MarvelItem
import com.example.marvelcomposee.ui.theme.MarvelScreen

@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : MarvelItem> MarvelItemsListScreen(
    loading: Boolean = false,
    items: List<T>,
    onClick: (Character) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.dimen.app_marvel))
                }
            )
        }
    ) {padding ->
        MarvelItemsList(
            loading = loading,
            items = items,
            onClick = onClick,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun <T : MarvelItem> MarvelItemsList(
    loading: Boolean,
    items: List<T>,
    onClick: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if(loading) {
            CircularProgressIndicator()
        }
        if(items.isNotEmpty()){
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(4.dp),
                modifier = modifier
            ) {
                items(items) {
                    MarvelListItem(
                        marvelItem = it,
                        modifier = Modifier.clickable { onClick(it) }
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun MarvelItemsListPreview() {
    MarvelScreen {
        MarvelItemsList(
            loading = true,
            items = emptyList(),
            onClick = {} )
    }
}