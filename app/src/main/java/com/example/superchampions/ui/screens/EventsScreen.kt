package com.example.superchampions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.superchampions.ui.components.CardEvento
import com.example.superchampions.ui.theme.CorPrimaria
import com.example.superchampions.ui.viewmodel.EventosUiState
import com.example.superchampions.ui.viewmodel.EventosViewModel

@Composable
fun EventsScreen(viewModel: EventosViewModel = viewModel(factory = EventosViewModel.Factory)) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Próximos Eventos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (uiState) {
            is EventosUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = CorPrimaria)
                }
            }
            is EventosUiState.Success -> {
                val eventos = (uiState as EventosUiState.Success).eventos
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(eventos) { evento ->
                        CardEvento(evento = evento, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
            is EventosUiState.Error -> {
                val mensagem = (uiState as EventosUiState.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = mensagem)
                }
            }
        }
    }
}
