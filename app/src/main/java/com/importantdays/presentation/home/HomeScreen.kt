package com.importantdays.presentation.home

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.importantdays.R
import com.importantdays.presentation.components.InterstitialAdManager
import com.importantdays.presentation.components.PlaceholderBannerAd
import com.importantdays.presentation.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    onOpenDrawer: () -> Unit = {}
) {
    val context = LocalContext.current
    val adManager = remember { InterstitialAdManager(context) }

    val monthDayCounts by viewModel.monthDayCounts.collectAsState()

    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("Important Days", "History")

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                title = {
                    Text(
                        text = androidx.compose.ui.res.stringResource(R.string.app_name),
                        fontWeight = FontWeight.ExtraBold
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Search.route) }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(title, fontWeight = FontWeight.Bold) }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> ImportantDaysContent(navController, monthDayCounts, adManager)
                    1 -> HistoryContent(navController, adManager)
                }
            }
        }
    }
}

val MonthGradients = listOf(
    Brush.linearGradient(listOf(Color(0xFF4CA1AF), Color(0xFFC4E0E5))),
    Brush.linearGradient(listOf(Color(0xFFff9966), Color(0xFFff5e62))),
    Brush.linearGradient(listOf(Color(0xFF00b09b), Color(0xFF96c93d))),
    Brush.linearGradient(listOf(Color(0xFFF7971E), Color(0xFFFFD200))),
    Brush.linearGradient(listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0))),
    Brush.linearGradient(listOf(Color(0xFFFF512F), Color(0xFFDD2476))),
    Brush.linearGradient(listOf(Color(0xFF1D976C), Color(0xFF93F9B9))),
    Brush.linearGradient(listOf(Color(0xFF1CB5E0), Color(0xFF000851))),
    Brush.linearGradient(listOf(Color(0xFFffb347), Color(0xFFffcc33))),
    Brush.linearGradient(listOf(Color(0xFFDA22FF), Color(0xFF9733EE))),
    Brush.linearGradient(listOf(Color(0xFF4facfe), Color(0xFF00f2fe))),
    Brush.linearGradient(listOf(Color(0xFFED213A), Color(0xFF93291E)))
)

@Composable
fun ImportantDaysContent(
    navController: NavController,
    monthDayCounts: Map<Int, MonthCount>,
    adManager: InterstitialAdManager
) {
    val context = LocalContext.current as Activity
    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(8.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF1f4037), Color(0xFF99f2c8))))
                .padding(24.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.CalendarMonth,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Welcome Back!",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Discover important days worldwide.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }

        Text(
            text = "Browse by Month",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        val months = listOf(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(months.size) { index ->
                val monthIndex = index + 1
                val counts = monthDayCounts[monthIndex]

                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .shadow(4.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(MonthGradients[index])
                        .clickable {
                            adManager.showAdIfReadyAndExecute(context) {
                                navController.navigate(Screen.Month.passMonth(monthIndex))
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = months[index],
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        if (counts != null && (counts.national > 0 || counts.international > 0)) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${counts.national} Nat | ${counts.international} Int",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                    }
                }
            }
        }

        PlaceholderBannerAd()
    }
}

val EraGradients = listOf(
    Brush.linearGradient(listOf(Color(0xFF373B44), Color(0xFF4286f4))),
    Brush.linearGradient(listOf(Color(0xFFFF416C), Color(0xFFFF4B2B))),
    Brush.linearGradient(listOf(Color(0xFF00B4DB), Color(0xFF0083B0))),
    Brush.linearGradient(listOf(Color(0xFFf12711), Color(0xFFf5af19))),
    Brush.linearGradient(listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0)))
)

@Composable
fun HistoryContent(navController: NavController, adManager: InterstitialAdManager) {
    val context = LocalContext.current as Activity
    val historyEras = com.importantdays.presentation.history.HistoryData.eras

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Explore History Timeline",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(historyEras.size) { index ->
                val gradient = EraGradients[index % EraGradients.size]

                Box(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .shadow(6.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(gradient)
                        .clickable {
                            adManager.showAdIfReadyAndExecute(context) {
                                navController.navigate(Screen.HistoryEra.passEraName(historyEras[index]))
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = historyEras[index],
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
