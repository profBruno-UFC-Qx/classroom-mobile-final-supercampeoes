package com.example.superchampions.ui.viewmodel

import com.example.superchampions.model.Atleta

sealed interface RankingUiState {
    data object Loading : RankingUiState
    data class Success(
        val atletas: List<Atleta> = emptyList()
    ) : RankingUiState
    data class Error(val message: String) : RankingUiState
}
