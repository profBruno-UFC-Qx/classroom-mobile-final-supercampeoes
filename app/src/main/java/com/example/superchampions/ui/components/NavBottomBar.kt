package com.example.superchampions.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.example.superchampions.ui.navigation.EventsScreenRoute
import com.example.superchampions.ui.navigation.HomeScreenRoute
import com.example.superchampions.ui.navigation.ProfileScreenRoute
import com.example.superchampions.ui.navigation.RankingScreenRoute

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: NavKey
)

@Composable
fun NavBottomBar(
    currentRoute: NavKey?,
    onNavigate: (NavKey) -> Unit
) {
    val items = listOf(
        BottomNavItem("Início", Icons.Default.Home, HomeScreenRoute),
        BottomNavItem("Eventos", Icons.Default.Event, EventsScreenRoute),
        BottomNavItem("Ranking", Icons.Default.EmojiEvents, RankingScreenRoute),
        BottomNavItem("Perfil", Icons.Default.Person, ProfileScreenRoute)
    )

    NavigationBar {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(item.route) },
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}
