package com.example.marvelcomposee.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.example.marvelcomposee.data.entities.Event
import com.example.marvelcomposee.data.entities.Result
import com.example.marvelcomposee.data.repositories.EventRepository
import kotlinx.coroutines.launch

class EventsViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(items = EventRepository.get())
        }
    }
    data class UiState(
        val loading : Boolean = false,
        val items: Result<List<Event>> = emptyList<Event>().right()
    )
}