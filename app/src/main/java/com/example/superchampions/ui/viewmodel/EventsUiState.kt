package com.example.superchampions.ui.viewmodel

import com.example.superchampions.model.Event

sealed interface EventsUiState {
    data object Loading : EventsUiState
    
    data class Success(
        val events: List<Event>,
        val searchQuery: String = "",
        val selectedTab: Int = 0
    ) : EventsUiState


    data class Error(val message: String) : EventsUiState
}
