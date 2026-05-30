package com.example.superchampions.ui.viewmodel

import com.example.superchampions.model.Atleta
import com.example.superchampions.model.Evento
import com.example.superchampions.model.Perfil

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(
        val topAtletas: List<Atleta> = emptyList(),
        val eventos: List<Evento> = emptyList(),
        val perfil: Perfil? = null
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
