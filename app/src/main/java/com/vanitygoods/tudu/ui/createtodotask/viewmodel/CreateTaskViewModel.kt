package com.vanitygoods.tudu.ui.createtodotask.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.vanitygoods.tudu.data.Repository
import com.vanitygoods.tudu.data.TodoTask
import com.vanitygoods.tudu.util.DateTimeFormats
import com.vanitygoods.tudu.util.SingleLiveEvent
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId


class CreateTaskViewModel(application: Application) : AndroidViewModel(application), CreateTaskViewModelContract {

    override val dismissConfirmationDialog = SingleLiveEvent<Boolean>()
    override val showConfirmationDialog = SingleLiveEvent<Boolean>()
    override val showDatePicker = SingleLiveEvent<Boolean>()
    override val showTimePicker = SingleLiveEvent<Boolean>()
    override val time = MutableLiveData<LocalTime>().apply { value = LocalTime.now(ZoneId.systemDefault()) }
    override val date = MutableLiveData<LocalDate>().apply { value = LocalDate.now(ZoneId.systemDefault()) }
    override val finishActivity = MutableLiveData<Boolean>()
    override val dateString: LiveData<String> = Transformations.map(date, { it.format(DateTimeFormats.dateFormat) })
    override val timeString: LiveData<String> = Transformations.map(time, { it.format(DateTimeFormats.timeFormat) })
    private var title = ""
    private var summary = ""
    private var isImportant: Boolean = false
    private val repository = Repository(application)

    override fun createTask() {
        if (title.isEmpty()) return

        val dueDateTime = LocalDateTime.of(date.value, time.value)
        val addTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
        val task = TodoTask(
            title = title,
            summary = summary,
            dueDate = dueDateTime,
            createdDateTime = addTime,
            isImportant = isImportant)

        repository.createTodoTask(task, { finishActivity.value = true })
    }

    override fun onDateSet(year: Int, month: Int, dayOfMonth: Int) {
        date.value = LocalDate.of(year, month, dayOfMonth)
    }

    override fun onDateClicked() {
        showDatePicker.value = true
    }

    override fun onTimeSet(hour: Int, minute: Int) {
        time.value = LocalTime.of(hour, minute)
    }

    override fun onTimeClicked() {
        showTimePicker.value = true
    }

    override fun hideConfirmationDialog() {
        dismissConfirmationDialog.value = true
    }

    override fun finishActivity() {
        finishActivity.value = true
    }

    override fun onBackPressed() {
        if (!title.isEmpty() || !summary.isEmpty()) {
            showConfirmationDialog.value = true
        } else {
            finishActivity()
        }
    }

    override fun onTitleChanged(title: String) {
        this.title = title
    }

    override fun onSummaryChanged(summary: String) {
        this.summary = summary
    }

    override fun onCheckBoxClicked(isChecked: Boolean) {
        isImportant = isChecked
    }
}