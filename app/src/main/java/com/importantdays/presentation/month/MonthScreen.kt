package com.importantdays.presentation.month

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.importantdays.presentation.components.EmptyState
import com.importantdays.presentation.components.ImportantDayCard
import com.importantdays.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthScreen(
    navController: NavController,
    month: Int,
    viewModel: MonthViewModel = hiltViewModel()
) {
    val days by viewModel.days.collectAsState()
    val monthName = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )[month - 1]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(monthName) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (days.isEmpty()) {
            EmptyState(message = "No important days found for $monthName.", modifier = Modifier.padding(paddingValues))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(days) { day ->
                    ImportantDayCard(
                        day = day,
                        monthName = monthName,
                        onClick = { navController.navigate(Screen.Details.passDayId(day.id)) }
                    )
                }
            }
        }
    }
}
