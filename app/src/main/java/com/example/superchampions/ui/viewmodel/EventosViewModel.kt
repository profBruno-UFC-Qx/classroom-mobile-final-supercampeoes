package com.example.superchampions.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.superchampions.repository.CampeoesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventosViewModel(private val repository: CampeoesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<EventosUiState>(EventosUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        carregarEventos()
    }

    fun carregarEventos() {
        viewModelScope.launch {
            _uiState.value = EventosUiState.Loading
            _uiState.update {
                try {
                    val eventos = repository.getEventosProximos()
                    EventosUiState.Success(eventos = eventos)
                } catch (e: Exception) {
                    EventosUiState.Error("Erro ao carregar eventos: ${e.message}")
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = CampeoesRepository()
                EventosViewModel(repository = repository)
            }
        }
    }
}
