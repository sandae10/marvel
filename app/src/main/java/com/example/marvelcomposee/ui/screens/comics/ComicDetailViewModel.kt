package com.example.marvelcomposee.ui.screens.comics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.marvelcomposee.data.entities.Comic
import com.example.marvelcomposee.data.entities.Result
import com.example.marvelcomposee.data.repositories.ComicsRepository
import com.example.marvelcomposee.ui.navigation.NavArg
import kotlinx.coroutines.launch

class ComicDetailViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(comic = ComicsRepository.find(id))
        }
    }
    data class UiState(
        val loading : Boolean = false,
        val comic : Result<Comic?> = Either.Right(null)
    )
}