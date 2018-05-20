package com.vanitygoods.tudu.ui.main

import com.vanitygoods.tudu.data.TodoTask
import org.threeten.bp.LocalDate

const val DATE_TYPE = 0
const val TASK_TYPE = 1
const val UNDEFINED_ID: Long = -1

data class ItemWrapper(val item: Any,
                       val type: Int = if (item is LocalDate) DATE_TYPE else TASK_TYPE) {

    fun getItemId(): Long {
        return when (item) {
            is LocalDate -> item.toEpochDay()
            is TodoTask  -> item.id ?: UNDEFINED_ID
            else         -> UNDEFINED_ID
        }
    }
}
