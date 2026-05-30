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

class RankingViewModel(private val repository: CampeoesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<RankingUiState>(RankingUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        carregarRanking()
    }

    fun carregarRanking() {
        viewModelScope.launch {
            _uiState.value = RankingUiState.Loading
            _uiState.update {
                try {
                    val atletas = repository.getRanking()
                    RankingUiState.Success(atletas = atletas)
                } catch (e: Exception) {
                    RankingUiState.Error("Erro ao carregar ranking: ${e.message}")
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = CampeoesRepository()
                RankingViewModel(repository = repository)
            }
        }
    }
}