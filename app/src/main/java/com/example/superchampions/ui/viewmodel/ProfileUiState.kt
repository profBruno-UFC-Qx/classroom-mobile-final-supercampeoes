package com.example.superchampions.ui.viewmodel

import com.example.superchampions.model.Perfil

sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(val perfil: Perfil) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}
