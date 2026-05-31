package com.example.superchampions.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.superchampions.model.EventStatus
import com.example.superchampions.repository.ChampionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ChampionsRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadHighlights()
    }

    fun loadHighlights() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                try {
                    val top = repository.getRanking().take(3)
                    val events = repository.getEvents().filter { it.status == EventStatus.OPEN }
                    val profile = repository.getProfile()
                    HomeUiState.Success(topAthletes = top, events = events, profile = profile)
                } catch (e: Exception) {
                    HomeUiState.Error("Erro ao carregar destaques: ${e.message}")
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = ChampionsRepository()
                HomeViewModel(repository = repository)
            }
        }
    }
}
