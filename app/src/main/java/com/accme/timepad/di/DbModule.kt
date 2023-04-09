package com.accme.timepad.di

import android.content.Context
import androidx.room.Room
import com.accme.timepad.persistence.AppDatabase
import com.accme.timepad.persistence.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModule {
    @Singleton
    @Provides
    fun provideTaskDai(appDatabase: AppDatabase): TaskDao = appDatabase.taskDao

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "time_pad_db")
            .fallbackToDestructiveMigration()
            .build()
}