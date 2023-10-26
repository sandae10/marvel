package com.example.marvelcomposee.ui.screens.comics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.example.marvelcomposee.data.entities.Comic
import com.example.marvelcomposee.data.entities.Result
import com.example.marvelcomposee.data.repositories.ComicsRepository
import kotlinx.coroutines.launch

class ComicViewModel : ViewModel() {

    val state =
        Comic.Format.values().associate { it to mutableStateOf(UiState()) }

    fun formatRequested(format : Comic.Format ) {
        val uiState = state.getValue(format)
        if (uiState.value.items.isNotEmpty()) return

        viewModelScope.launch {
            uiState.value = UiState(loading = true)
            uiState.value = UiState(items = ComicsRepository.get(format))
        }
    }
    data class  UiState(
        val loading : Boolean = false,
        val items : Result<List<Comic>> = emptyList<Comic>().right()
    )
}

