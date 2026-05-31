package com.example.superchampions.ui.viewmodel

import com.example.superchampions.model.Athlete
import com.example.superchampions.model.Event
import com.example.superchampions.model.Profile

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(
        val topAthletes: List<Athlete> = emptyList(),
        val events: List<Event> = emptyList(),
        val profile: Profile? = null
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
