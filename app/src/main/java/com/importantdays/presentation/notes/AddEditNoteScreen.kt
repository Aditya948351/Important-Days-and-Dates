package com.importantdays.presentation.notes

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorDefaults
import com.importantdays.worker.NoteReminderReceiver
import com.importantdays.presentation.navigation.Screen
import com.importantdays.presentation.notes.utils.DocumentGenerators
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val rawContentState = viewModel.noteContent.value
    val context = LocalContext.current

    val state = rememberRichTextState()

    LaunchedEffect(rawContentState) {
        if (state.toMarkdown() != rawContentState && state.toHtml() != rawContentState) {

            state.setMarkdown(rawContentState)
        }
    }

    var showReminderDialog by remember { mutableStateOf(false) }
    var showShareDialog by remember { mutableStateOf(false) }

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val ocrText = savedStateHandle?.get<String>("ocr_text")
    LaunchedEffect(ocrText) {
        if (!ocrText.isNullOrEmpty()) {
            state.setMarkdown(state.toMarkdown() + "\n" + ocrText)
            savedStateHandle.remove<String>("ocr_text")
        }
    }

    BackHandler {
        viewModel.onEvent(AddEditNoteEvent.EnteredContent(state.toMarkdown()))
        viewModel.onEvent(AddEditNoteEvent.SaveNote)
        navController.navigateUp()
    }

    if (showReminderDialog) {
        AlertDialog(
            onDismissRequest = { showReminderDialog = false },
            title = { Text("Set Spaced Repetition") },
            text = { Text("When would you like to be reminded to revise this note?") },
            confirmButton = {
                TextButton(onClick = {
                    scheduleReminder(context, titleState, 1)
                    showReminderDialog = false
                    Toast.makeText(context, "Reminder set for 1 Day", Toast.LENGTH_SHORT).show()
                }) { Text("In 1 Day") }
            },
            dismissButton = {
                TextButton(onClick = {
                    scheduleReminder(context, titleState, 3)
                    showReminderDialog = false
                    Toast.makeText(context, "Reminder set for 3 Days", Toast.LENGTH_SHORT).show()
                }) { Text("In 3 Days") }
            }
        )
    }

    if (showShareDialog) {
        AlertDialog(
            onDismissRequest = { showShareDialog = false },
            title = { Text("Share Note") },
            text = { Text("How would you like to share this note?") },
            confirmButton = {
                TextButton(onClick = {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TITLE, titleState)

                        putExtra(Intent.EXTRA_TEXT, "Important Note: $titleState\n\n${state.annotatedString.text}")
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(sendIntent, null))
                    showShareDialog = false
                }) { Text("Text") }
            },
            dismissButton = {
                Row {
                    TextButton(onClick = {
                        val uri = DocumentGenerators.generatePdfFromNote(context, titleState, state.annotatedString.text)
                        if (uri != null) {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "application/pdf"
                                putExtra(Intent.EXTRA_STREAM, uri)
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share PDF"))
                        }
                        showShareDialog = false
                    }) { Text("PDF") }
                    TextButton(onClick = {
                        val uri = DocumentGenerators.generateImageFromNote(context, titleState, state.annotatedString.text)
                        if (uri != null) {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "image/png"
                                putExtra(Intent.EXTRA_STREAM, uri)
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share Image"))
                        }
                        showShareDialog = false
                    }) { Text("Image") }
                }
            }
        )
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent(state.toMarkdown()))
                        viewModel.onEvent(AddEditNoteEvent.SaveNote)
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showShareDialog = true }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { showReminderDialog = true }) {
                        Icon(Icons.Default.Alarm, contentDescription = "Reminder")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                tonalElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        state.toggleSpanStyle(SpanStyle(background = Color(0xFFFFF740)))
                    }) {
                        Icon(
                            Icons.Default.Highlight,
                            contentDescription = "Highlight",
                            tint = if (state.currentSpanStyle.background == Color(0xFFFFF740)) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                    }
                    IconButton(onClick = {
                        state.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    }) {
                        Icon(
                            Icons.Default.FormatBold,
                            contentDescription = "Bold",
                            tint = if (state.currentSpanStyle.fontWeight == FontWeight.Bold) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                    }
                    IconButton(onClick = {
                        state.toggleUnorderedList()
                    }) {
                        Icon(
                            Icons.Default.FormatListBulleted,
                            contentDescription = "Bullet List",
                            tint = if (state.isUnorderedList) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                    }
                    IconButton(onClick = {
                        state.toggleOrderedList()
                    }) {
                        Icon(
                            Icons.Default.FormatListNumbered,
                            contentDescription = "Numbered List",
                            tint = if (state.isOrderedList) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                    }
                    IconButton(onClick = {
                        val sdf = SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault())
                        val timestamp = sdf.format(Date())
                        val currentText = state.annotatedString.text
                        val prefix = if (currentText.isEmpty() || currentText.endsWith("\n")) "" else "\n"
                        state.setMarkdown(state.toMarkdown() + prefix + timestamp)
                    }) {
                        Icon(Icons.Default.Schedule, contentDescription = "Timestamp")
                    }
                    IconButton(onClick = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent(state.toMarkdown()))
                        navController.navigate(Screen.OcrCamera.route)
                    }) {
                        Icon(Icons.Default.CameraAlt, contentDescription = "Scan Text")
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            BasicTextField(
                value = titleState,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (titleState.isEmpty()) {
                        Text(
                            text = "Title",
                            style = TextStyle(fontSize = 24.sp, color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            RichTextEditor(
                state = state,
                modifier = Modifier.fillMaxSize(),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                colors = RichTextEditorDefaults.richTextEditorColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Start writing your notes...",
                        style = TextStyle(fontSize = 18.sp, color = Color.Gray)
                    )
                }
            )
        }
    }
}

fun scheduleReminder(context: Context, title: String, days: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, NoteReminderReceiver::class.java).apply {
        putExtra("noteTitle", title)
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        System.currentTimeMillis().toInt(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val timeInMillis = System.currentTimeMillis() + (days * 24 * 60 * 60 * 1000L)

    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    timeInMillis,
                    pendingIntent
                )
            }
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pendingIntent
            )
        }
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}
