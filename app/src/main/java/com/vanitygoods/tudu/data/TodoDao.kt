package com.vanitygoods.tudu.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import org.threeten.bp.LocalDateTime


@Dao
interface TodoDao {

    @Query("SELECT * FROM todotask ORDER BY dueDate ASC")
    fun getAll(): LiveData<List<TodoTask>>

    @Query("UPDATE todotask SET isDone = :isDone, doneDateTime = :doneDateTime  WHERE id = :id")
    fun setDone(isDone: Boolean, id: Long, doneDateTime: LocalDateTime)

    @Insert
    fun insert(todoTask: TodoTask)

    @Delete
    fun delete(todoTask: TodoTask)
}