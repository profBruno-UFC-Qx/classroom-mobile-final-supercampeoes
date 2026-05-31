package com.example.superchampions.ui.viewmodel

import com.example.superchampions.model.Athlete

sealed interface RankingUiState {
    data object Loading : RankingUiState
    data class Success(
        val athletes: List<Athlete> = emptyList()
    ) : RankingUiState
    data class Error(val message: String) : RankingUiState
}
