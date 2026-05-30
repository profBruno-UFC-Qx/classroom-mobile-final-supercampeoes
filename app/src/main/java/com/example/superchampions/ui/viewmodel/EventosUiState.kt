package com.example.superchampions.ui.viewmodel

import com.example.superchampions.model.Evento

sealed interface EventosUiState {
    data object Loading : EventosUiState
    data class Success(
        val eventos: List<Evento> = emptyList()
    ) : EventosUiState
    data class Error(val message: String) : EventosUiState
}
