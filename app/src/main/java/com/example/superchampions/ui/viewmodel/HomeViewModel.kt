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

class HomeViewModel(private val repository: CampeoesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        carregarDestaques()
    }

    fun carregarDestaques() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            _uiState.update {
                try {
                    val topTres = repository.getRanking().take(3)
                    HomeUiState.Success(topAtletas = topTres)
                } catch (e: Exception) {
                    HomeUiState.Error("Erro ao carregar ranking: ${e.message}")
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = CampeoesRepository()
                HomeViewModel(repository = repository)
            }
        }
    }
}
