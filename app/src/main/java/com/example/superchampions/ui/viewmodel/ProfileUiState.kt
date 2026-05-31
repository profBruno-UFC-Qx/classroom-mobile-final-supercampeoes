package com.example.superchampions.ui.viewmodel

import com.example.superchampions.model.Profile

sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(val profile: Profile) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}
