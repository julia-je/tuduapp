package com.vanitygoods.tudu.ui.main.viewmodel

import android.arch.lifecycle.LiveData
import com.vanitygoods.tudu.ui.main.ItemWrapper


interface ToDoListViewModelContract : OnDoneTaskListener {
    val listOfTasks: LiveData<List<ItemWrapper>>
    val showEmptyView: LiveData<Boolean>
}

interface OnDoneTaskListener {
    fun onDoneStatusChanged(isDone: Boolean, taskId: Long)
}