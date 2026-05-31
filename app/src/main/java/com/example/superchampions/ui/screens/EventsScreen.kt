package com.example.superchampions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.superchampions.ui.components.EventCard
import com.example.superchampions.ui.theme.CorPrimaria
import com.example.superchampions.ui.theme.CorSecundaria
import com.example.superchampions.ui.theme.CorTextoSecundario
import com.example.superchampions.ui.viewmodel.EventsUiState
import com.example.superchampions.ui.viewmodel.EventsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(viewModel: EventsViewModel = viewModel(factory = EventsViewModel.Factory)) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        val searchQuery = if (uiState is EventsUiState.Success) (uiState as EventsUiState.Success).searchQuery else ""
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            placeholder = { Text("Buscar eventos...", color = CorTextoSecundario)},
            shape = RoundedCornerShape(16.dp),
        )

        val selectedTab = if (uiState is EventsUiState.Success) (uiState as EventsUiState.Success).selectedTab else 0
        PrimaryTabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            contentColor = CorPrimaria,
            indicator = {
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(selectedTab),
                    height = 3.dp,
                    color = CorSecundaria
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { viewModel.updateTab(0) },
                text = { 
                    Text(
                        text = "Próximos", 
                        fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 15.sp,
                        color = if (selectedTab == 0) CorPrimaria else CorTextoSecundario
                    ) 
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { viewModel.updateTab(1) },
                text = { 
                    Text(
                        text = "Encerrados", 
                        fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 15.sp,
                        color = if (selectedTab == 1) CorPrimaria else CorTextoSecundario
                    ) 
                }
            )
        }
        when (uiState) {
            is EventsUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = CorSecundaria)
                }
            }
            is EventsUiState.Success -> {
                val events = (uiState as EventsUiState.Success).events
                if (events.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Nenhum evento encontrado.", color = CorTextoSecundario)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(events) { event ->
                            EventCard(event = event, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
            is EventsUiState.Error -> {
                val message = (uiState as EventsUiState.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = message, color = CorTextoSecundario)
                }
            }
        }
    }
}
