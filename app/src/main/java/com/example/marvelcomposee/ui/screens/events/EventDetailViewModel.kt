package com.example.marvelcomposee.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.marvelcomposee.data.entities.Event
import com.example.marvelcomposee.data.entities.Result
import com.example.marvelcomposee.data.repositories.EventRepository
import com.example.marvelcomposee.ui.navigation.NavArg
import kotlinx.coroutines.launch

class EventDetailViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(event = EventRepository.find(id))
        }
    }
    data class UiState(
        val loading : Boolean = false,
        val event : Result<Event?> = Either.Right(null)
    )
}