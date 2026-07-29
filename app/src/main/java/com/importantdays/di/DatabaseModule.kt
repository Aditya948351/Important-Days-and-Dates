package com.importantdays.di

import android.content.Context
import androidx.room.Room
import com.importantdays.data.dao.DayDao
import com.importantdays.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "important_days_db"
        )
        .addMigrations(AppDatabase.MIGRATION_4_5, AppDatabase.MIGRATION_5_6, AppDatabase.MIGRATION_6_7)
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideDayDao(database: AppDatabase): DayDao {
        return database.dayDao()
    }

    @Provides
    fun provideNoteDao(database: AppDatabase): com.importantdays.data.dao.NoteDao {
        return database.noteDao()
    }
}
