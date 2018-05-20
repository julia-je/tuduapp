package com.vanitygoods.tudu.data

import android.arch.persistence.room.Room
import android.content.Context
import android.support.annotation.WorkerThread


object DbAccess {
    private var db: TodoTaskDatabase? = null

    @WorkerThread
    private fun getDatabase(applicationContext: Context): TodoTaskDatabase {
        if (db == null) {
            db = Room.databaseBuilder(applicationContext,
                                      TodoTaskDatabase::class.java, "todo_database")
                .fallbackToDestructiveMigration()
                .build()
        }
        return db!!
    }

    @WorkerThread
    fun getTodoDao(applicationContext: Context): TodoDao {
        return getDatabase(applicationContext).todoDao()
    }
}