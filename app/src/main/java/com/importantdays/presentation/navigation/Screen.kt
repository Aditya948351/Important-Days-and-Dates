package com.importantdays.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Month : Screen("month_screen/{month}") {
        fun passMonth(month: Int) = "month_screen/$month"
    }
    object Details : Screen("details_screen/{dayId}") {
        fun passDayId(id: Int) = "details_screen/$id"
    }
    object About : Screen("about_screen")
    object Settings : Screen("settings_screen")
    object PrivacyPolicy : Screen("privacy_policy_screen")
    object Licenses : Screen("licenses_screen")
    object Search : Screen("search_screen")
    object HistoryEra : Screen("history_era_screen/{eraName}") {
        fun passEraName(eraName: String) = "history_era_screen/$eraName"
    }
    object HistoryEvent : Screen("history_event_screen/{eventTitle}") {
        fun passEventTitle(eventTitle: String) = "history_event_screen/$eventTitle"
    }
    object Flashcards : Screen("flashcards_screen")
    object Notes : Screen("notes_screen")
    object AddEditNote : Screen("add_edit_note_screen?noteId={noteId}") {
        fun passNoteArgs(noteId: Int = -1): String {
            return "add_edit_note_screen?noteId=$noteId"
        }
    }
    object OcrCamera : Screen("ocr_camera_screen")
}