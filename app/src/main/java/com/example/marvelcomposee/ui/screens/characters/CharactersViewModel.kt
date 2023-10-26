package com.example.marvelcomposee.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.example.marvelcomposee.data.entities.Character
import com.example.marvelcomposee.data.entities.Result
import com.example.marvelcomposee.data.repositories.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(characters = CharacterRepository.get())
        }
    }
    data class UiState(
        val loading : Boolean = false,
        val characters: Result<List<Character>> = emptyList<Character>().right()
    )
}