package com.example.marvelcomposee.ui.screens.charactersDetail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalUriHandler

@Composable
fun AppBarOverflowMenu(urls: List<com.example.marvelcomposee.data.network.entities.Url>) {

    var showMenu by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current


    IconButton(onClick = { showMenu = !showMenu}) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More Actions"
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false}
        ) {
             urls.forEach{
                DropdownMenuItem(
                    text = { /*TODO*/ },
                    onClick = {
                        uriHandler.openUri(it.url)
                        showMenu = false
                    })
            }
        }
    }
    
}