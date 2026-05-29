package com.example.superchampions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack

import com.example.superchampions.ui.components.NavBottomBar
import com.example.superchampions.ui.navigation.EventsScreenRoute
import com.example.superchampions.ui.navigation.HomeScreenRoute
import com.example.superchampions.ui.navigation.ProfileScreenRoute
import com.example.superchampions.ui.navigation.RankingScreenRoute
import com.example.superchampions.ui.screens.EventsScreen
import com.example.superchampions.ui.screens.HomeScreen
import com.example.superchampions.ui.screens.ProfileScreen
import com.example.superchampions.ui.screens.RankingScreen
import com.example.superchampions.ui.theme.SuperChampionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperChampionsTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val backStack = rememberNavBackStack(HomeScreenRoute)

    var currentRoute: NavKey? = null
    if (backStack.isNotEmpty()) currentRoute = backStack.last()

    Scaffold(
        bottomBar = {
            NavBottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    if (currentRoute != route) {
                        backStack.add(route)
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            NavDisplay(
                backStack = backStack,
                onBack = {
                    if (backStack.size > 1) {
                        backStack.removeAt(backStack.size - 1)
                    }
                },
                entryProvider = entryProvider {
                    entry<HomeScreenRoute> { HomeScreen() }
                    entry<EventsScreenRoute> { EventsScreen() }
                    entry<RankingScreenRoute> { RankingScreen() }
                    entry<ProfileScreenRoute> { ProfileScreen() }
                }

            )
        }
    }
}