package com.vanitygoods.tudu.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.LocalDateTime


@Entity
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String,
    val summary: String?,
    val createdDateTime: LocalDateTime,
    val dueDate: LocalDateTime,
    val doneDateTime: LocalDateTime? = null,
    val isDone: Boolean = false,
    val isImportant: Boolean = false
                   )