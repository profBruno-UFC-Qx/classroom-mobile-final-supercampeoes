package com.example.superchampions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import com.example.superchampions.model.Atleta
import com.example.superchampions.ui.viewmodel.HomeUiState
import com.example.superchampions.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Super Campeões",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Top 3 do Ranking",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (uiState) {
            is HomeUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is HomeUiState.Success -> {
                val atletas = (uiState as HomeUiState.Success).topAtletas
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(atletas) { atleta ->
                        CartaoAtletaHome(atleta = atleta)
                    }
                }
            }
            is HomeUiState.Error -> {
                val mensagem = (uiState as HomeUiState.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = mensagem)
                }
            }
        }
    }
}

@Composable
fun CartaoAtletaHome(atleta: Atleta) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "#${atleta.posicao} ${atleta.nome}", style = MaterialTheme.typography.titleSmall)
            Text(text = "Academia: ${atleta.academia}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Pontos: ${atleta.pontos}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
