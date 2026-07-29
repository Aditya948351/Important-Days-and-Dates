package com.importantdays.presentation.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Note
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.importantdays.presentation.about.AboutScreen
import com.importantdays.presentation.details.DayDetailsScreen
import com.importantdays.presentation.home.HomeScreen
import com.importantdays.presentation.month.MonthScreen
import com.importantdays.presentation.search.SearchScreen
import com.importantdays.presentation.splash.SplashScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavGraph(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.6f)) {
                Spacer(Modifier.height(16.dp))
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Note, contentDescription = null) },
                    label = { Text("Daily Notes") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Screen.Notes.route)
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {

            composable(route = Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    onOpenDrawer = { scope.launch { drawerState.open() } }
                )
            }

            composable(route = Screen.Notes.route) {
                com.importantdays.presentation.notes.NotesScreen(navController = navController)
            }

            composable(route = Screen.Flashcards.route) {
                com.importantdays.presentation.notes.FlashcardScreen(navController = navController)
            }

            composable(
                route = Screen.AddEditNote.route,
                arguments = listOf(
                    navArgument("noteId") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) {
                com.importantdays.presentation.notes.AddEditNoteScreen(navController = navController)
            }

            composable(
                route = Screen.Month.route,
                arguments = listOf(navArgument("month") { type = NavType.IntType })
            ) { backStackEntry ->
                val month = backStackEntry.arguments?.getInt("month") ?: 1
                MonthScreen(navController = navController, month = month)
            }

            composable(
                route = Screen.Details.route,
                arguments = listOf(navArgument("dayId") { type = NavType.IntType })
            ) { backStackEntry ->
                val dayId = backStackEntry.arguments?.getInt("dayId") ?: 0
                DayDetailsScreen(navController = navController, dayId = dayId)
            }

            composable(route = Screen.Search.route) {
                SearchScreen(navController = navController)
            }

            composable(route = Screen.Settings.route) {
                com.importantdays.presentation.about.SettingsScreen(navController = navController)
            }

            composable(route = Screen.About.route) {
                com.importantdays.presentation.about.AboutScreen(navController = navController)
            }

            composable(route = Screen.PrivacyPolicy.route) {
                com.importantdays.presentation.about.PrivacyPolicyScreen(navController = navController)
            }

            composable(route = Screen.Licenses.route) {
                com.importantdays.presentation.about.LicensesScreen(navController = navController)
            }

            composable(
                route = Screen.HistoryEra.route,
                arguments = listOf(navArgument("eraName") { type = NavType.StringType })
            ) { backStackEntry ->
                val eraName = backStackEntry.arguments?.getString("eraName") ?: ""
                com.importantdays.presentation.history.HistoryEraDetailsScreen(
                    navController = navController,
                    eraName = eraName
                )
            }

            composable(
                route = Screen.HistoryEvent.route,
                arguments = listOf(navArgument("eventTitle") { type = NavType.StringType })
            ) { backStackEntry ->
                val eventTitle = backStackEntry.arguments?.getString("eventTitle") ?: ""
                com.importantdays.presentation.history.HistoryEventDetailsScreen(
                    navController = navController,
                    eventTitle = eventTitle
                )
            }

            composable(route = Screen.OcrCamera.route) {
                com.importantdays.presentation.notes.OcrCameraScreen(navController = navController)
            }
        }
    }
}
