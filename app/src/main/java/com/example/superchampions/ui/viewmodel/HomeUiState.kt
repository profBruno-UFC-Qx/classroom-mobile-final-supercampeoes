package com.example.superchampions.ui.viewmodel

import com.example.superchampions.model.Atleta

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(
        val topAtletas: List<Atleta> = emptyList()
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
