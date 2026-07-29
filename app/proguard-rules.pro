# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

# --- Hilt/Dagger ---
-keep class dagger.** { *; }
-dontwarn dagger.**
-keep class hilt_aggregated_deps.** { *; }
-dontwarn hilt_aggregated_deps.**
-keep class dagger.hilt.internal.aggregatedroot.codegen.** { *; }
-dontwarn dagger.hilt.internal.aggregatedroot.codegen.**

# --- Room ---
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# --- Coroutines ---
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}

# --- Compose ---
-keep class androidx.compose.** { *; }
