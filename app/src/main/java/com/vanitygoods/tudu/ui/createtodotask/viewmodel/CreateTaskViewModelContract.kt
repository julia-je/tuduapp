package com.vanitygoods.tudu.ui.createtodotask.viewmodel

import android.arch.lifecycle.LiveData
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime


interface CreateTaskViewModelContract {
    val finishActivity: LiveData<Boolean>
    val date: LiveData<LocalDate>
    val time: LiveData<LocalTime>
    val dateString: LiveData<String>
    val timeString: LiveData<String>
    val showDatePicker: LiveData<Boolean>
    val showTimePicker: LiveData<Boolean>
    val showConfirmationDialog: LiveData<Boolean>
    val dismissConfirmationDialog: LiveData<Boolean>
    fun createTask()
    fun onDateSet(year: Int, month: Int, dayOfMonth: Int)
    fun onTimeSet(hour: Int, minute: Int)
    fun onDateClicked()
    fun onTimeClicked()
    fun hideConfirmationDialog()
    fun finishActivity()
    fun onBackPressed()
    fun onTitleChanged(title: String)
    fun onSummaryChanged(summary: String)
    fun onCheckBoxClicked(isChecked: Boolean)

}