package com.accme.timepad.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.accme.timepad.data.Task
import com.accme.timepad.utils.Converters

@Database(
    entities = [Task::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
}