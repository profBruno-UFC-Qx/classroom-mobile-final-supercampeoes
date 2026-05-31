package com.example.superchampions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.SportsKabaddi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.superchampions.model.Athlete
import com.example.superchampions.model.Event
import com.example.superchampions.model.Profile
import com.example.superchampions.ui.components.EventCard
import com.example.superchampions.ui.components.ActionItem
import com.example.superchampions.ui.components.RankingPersonCard
import com.example.superchampions.ui.theme.CorContainer
import com.example.superchampions.ui.theme.CorDourado
import com.example.superchampions.ui.theme.CorPrimaria
import com.example.superchampions.ui.theme.CorSecundaria
import com.example.superchampions.ui.theme.CorTextoSecundario
import com.example.superchampions.ui.viewmodel.HomeUiState
import com.example.superchampions.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is HomeUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = CorSecundaria)
            }
        }
        is HomeUiState.Success -> {
            val state = uiState as HomeUiState.Success
            HomeContent(
                topAthletes = state.topAthletes,
                events = state.events,
                profile = state.profile
            )
        }
        is HomeUiState.Error -> {
            val mensagem = (uiState as HomeUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = mensagem, color = CorTextoSecundario)
            }
        }
    }
}

@Composable
fun HomeContent(topAthletes: List<Athlete>, events: List<Event>, profile: Profile?) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(16.dp)) }
        if (events.isNotEmpty()) {
            item { HeroBanner(event = events.first()) }
        }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        item { QuickActions() }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        item { EventsSection(events) }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        item { HomeRankingCard(topAthletes) }
        if (profile != null) {
            item { ProfileSummarySection(profile) }
        }
    }
}

@Composable
fun HeroBanner(event: Event) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        AsyncImage(
            model = event.imageUrl,
            contentDescription = event.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Surface(shape = RoundedCornerShape(100.dp), color = CorDourado) {
                Text(
                    text = "DESTAQUE",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    color = CorPrimaria,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event.title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${event.location} · ${event.date}",
                color = Color.White,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Premiação: ${event.prize}",
                color = CorDourado,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = CorSecundaria),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Ver detalhes", color = Color.White)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun QuickActions() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionItem(modifier = Modifier.weight(1f), icon = Icons.Default.AddCircleOutline, label = "Inscrições")
            ActionItem(modifier = Modifier.weight(1f), icon = Icons.Default.MilitaryTech, label = "Ranking")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionItem(modifier = Modifier.weight(1f), icon = Icons.Outlined.EventAvailable, label = "Eventos")
            ActionItem(modifier = Modifier.weight(1f), icon = Icons.Outlined.SportsKabaddi, label = "Minhas Lutas")
        }
    }
}

@Composable
fun EventsSection(events: List<Event>) {
    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Principais Eventos", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = CorPrimaria)
            TextButton(onClick = {}) {
                Text(text = "Ver todos", color = CorPrimaria, fontSize = 14.sp)
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = CorPrimaria,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(events) { evento ->
                EventCard(event = evento, modifier = Modifier.width(240.dp))
            }
        }
    }
}

@Composable
fun HomeRankingCard(athletes: List<Athlete>) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CorPrimaria)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = CorDourado,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Ranking Geral", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            athletes.forEachIndexed { index, athlete ->
                if (index > 0) Spacer(modifier = Modifier.height(8.dp))
                RankingPersonCard(position = index + 1, athlete = athlete)
            }
        }
    }
}

@Composable
fun ProfileSummarySection(profile: Profile) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Seu Resumo",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = CorPrimaria,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SummaryCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.EmojiEvents,
                label = "Pontos",
                value = "${profile.points}"
            )
            SummaryCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.WorkspacePremium,
                label = "Corda",
                value = profile.cord
            )
            SummaryCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Home,
                label = "Academia",
                value = profile.gym
            )
        }
    }
}

@Composable
fun SummaryCard(modifier: Modifier, icon: ImageVector, label: String, value: String) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CorContainer)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = CorSecundaria, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, fontSize = 11.sp, color = CorTextoSecundario)
            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = CorPrimaria,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
