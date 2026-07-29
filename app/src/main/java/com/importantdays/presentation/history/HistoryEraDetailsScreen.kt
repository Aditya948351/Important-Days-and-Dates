package com.importantdays.presentation.history

import android.app.Activity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.importantdays.presentation.components.InterstitialAdManager
import com.importantdays.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryEraDetailsScreen(navController: NavController, eraName: String) {
    val context = LocalContext.current as Activity
    val adManager = remember { InterstitialAdManager(context) }

    val isTimelineEra = eraName in listOf("Ancient Era", "Medieval Era", "Early Modern Era", "Modern Era", "Modern India")
    val timelineEvents = when (eraName) {
        "Ancient Era" -> HistoryData.ancientTimelineEvents
        "Medieval Era" -> HistoryData.medievalTimelineEvents
        "Early Modern Era" -> HistoryData.earlyModernTimelineEvents
        "Modern Era" -> HistoryData.modernEraTimelineEvents
        "Modern India" -> HistoryData.modernTimelineEvents
        else -> emptyList()
    }
    val standardEvents = HistoryData.getEventsForEra(eraName)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(eraName) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (isTimelineEra && timelineEvents.isNotEmpty()) {

            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 16.dp),
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                items(timelineEvents) { event ->
                    TimelineItem(event = event)
                }
            }
        } else {

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(standardEvents) { event ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                adManager.showAdIfReadyAndExecute(context) {
                                    navController.navigate(Screen.HistoryEvent.passEventTitle(event.title))
                                }
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = event.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = event.date,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = event.shortDetail,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimelineItem(event: TimelineHistoryEvent) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {

        Column(
            modifier = Modifier.weight(0.4f).padding(end = 8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = event.dateDisplay,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp, bottomEnd = 12.dp, topEnd = 2.dp),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Global Context",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                        modifier = Modifier.align(Alignment.End)
                    )
                    Text(
                        text = event.globalContextTitle,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.align(Alignment.End)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = event.globalContextDesc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f),
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }

        val lineColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        val nodeColor = MaterialTheme.colorScheme.primary
        Box(
            modifier = Modifier.width(24.dp).padding(top = 4.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Canvas(modifier = Modifier.fillMaxHeight().height(200.dp)) {
                drawLine(
                    color = lineColor,
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            }
            Surface(
                modifier = Modifier.size(12.dp).clip(CircleShape),
                color = nodeColor
            ) {}
        }

        Column(
            modifier = Modifier.weight(0.6f).padding(start = 8.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp, bottomEnd = 12.dp, topStart = 2.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = event.indianEventTitle,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = event.indianEventDesc,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
