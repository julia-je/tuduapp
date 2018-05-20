package com.vanitygoods.tudu.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(entities = [TodoTask::class], version = 2)
@TypeConverters((Converters::class))
abstract class TodoTaskDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
