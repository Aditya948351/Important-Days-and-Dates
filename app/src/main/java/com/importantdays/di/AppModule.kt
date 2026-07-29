package com.importantdays.di

import com.importantdays.data.repository.DayRepositoryImpl
import com.importantdays.domain.repository.DayRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindDayRepository(
        dayRepositoryImpl: DayRepositoryImpl
    ): DayRepository

    @Binds
    @Singleton
    abstract fun bindNoteRepository(
        noteRepositoryImpl: com.importantdays.data.repository.NoteRepositoryImpl
    ): com.importantdays.domain.repository.NoteRepository
}
