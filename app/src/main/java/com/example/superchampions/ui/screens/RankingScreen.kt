package com.example.superchampions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.superchampions.ui.viewmodel.RankingUiState
import com.example.superchampions.ui.viewmodel.RankingViewModel

@Composable
fun RankingScreen(viewModel: RankingViewModel = viewModel(factory = RankingViewModel.Factory)) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Ranking Geral",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (uiState) {
            is RankingUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is RankingUiState.Success -> {
                val atletas = (uiState as RankingUiState.Success).atletas
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(atletas) { atleta ->
                        CartaoAtleta(atleta = atleta)
                    }
                }
            }
            is RankingUiState.Error -> {
                val mensagem = (uiState as RankingUiState.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = mensagem)
                }
            }
        }
    }
}

@Composable
fun CartaoAtleta(atleta: Atleta) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "#${atleta.posicao} ${atleta.nome}", style = MaterialTheme.typography.titleSmall)
                Text(text = atleta.academia, style = MaterialTheme.typography.bodySmall)
                Text(text = "Corda: ${atleta.corda}", style = MaterialTheme.typography.bodySmall)
            }
            Text(text = "${atleta.pontos} pts", style = MaterialTheme.typography.titleMedium)
        }
    }
}
