package com.example.superchampions.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.superchampions.repository.ChampionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventsViewModel(private val repository: ChampionsRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<EventsUiState>(EventsUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        loadEvents()
    }

    private fun loadEvents() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                try {
                    val events = repository.getEvents()

                    EventsUiState.Success(
                        events = events,
                        searchQuery = "",
                        selectedTab = 0
                    )
                } catch (e: Exception) {
                    EventsUiState.Error("Erro ao carregar events: ${e.message}")
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { currentState ->
            if (currentState is EventsUiState.Success) {
                currentState.copy(searchQuery = query)
            } else {
                currentState
            }
        }
    }

    fun updateTab(index: Int) {
        _uiState.update { currentState ->
            if (currentState is EventsUiState.Success) {
                currentState.copy(selectedTab = index)
            } else {
                currentState
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = ChampionsRepository()
                EventsViewModel(repository = repository)
            }
        }
    }
}
