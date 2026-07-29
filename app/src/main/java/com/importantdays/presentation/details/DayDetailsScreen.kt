package com.importantdays.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.importantdays.presentation.components.CategoryChip
import com.importantdays.presentation.components.LoadingState
import com.importantdays.presentation.components.PlaceholderBannerAd

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayDetailsScreen(
    navController: NavController,
    dayId: Int,
    viewModel: DayDetailsViewModel = hiltViewModel()
) {
    val day by viewModel.day.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(day?.title ?: "Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            day?.let {
                FloatingActionButton(onClick = { viewModel.toggleFavorite() }) {
                    Icon(
                        imageVector = if (it.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }
            }
        }
    ) { paddingValues ->
        day?.let { currentDay ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                PlaceholderBannerAd(modifier = Modifier.padding(bottom = 16.dp))

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    val monthName = listOf(
                        "January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"
                    )[currentDay.month - 1]

                    Text(
                        text = "${currentDay.day} $monthName",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = currentDay.title,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CategoryChip(category = currentDay.category)

                    Spacer(modifier = Modifier.height(24.dp))

                    SectionTitle(title = "Description")
                    Text(
                        text = currentDay.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                PlaceholderBannerAd()
                Spacer(modifier = Modifier.height(16.dp))
            }
        } ?: run {
            LoadingState(modifier = Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}
