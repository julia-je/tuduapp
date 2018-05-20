package com.vanitygoods.tudu.ui.main.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.vanitygoods.tudu.data.Repository
import com.vanitygoods.tudu.data.TodoTask
import com.vanitygoods.tudu.ui.main.ItemWrapper


class ToDoListViewModel(application: Application) : AndroidViewModel(application), ToDoListViewModelContract {

    private val repository = Repository(application)

    override val listOfTasks: LiveData<List<ItemWrapper>> = Transformations.map(repository.getListOfTasks(), { getList(it) })

    override val showEmptyView: LiveData<Boolean> = Transformations.map(listOfTasks, { it == null || it.isEmpty() })

    override fun onDoneStatusChanged(isDone: Boolean, taskId: Long) {
        repository.updateDoneStatus(isDone, taskId)
    }

    private fun getList(listOfTasks: List<TodoTask>): List<ItemWrapper> {
        val result = mutableListOf<ItemWrapper>()
        val map = listOfTasks.groupBy { it.dueDate.toLocalDate() }

        map.keys.forEach {
            result.add(ItemWrapper(it))
            result.addAll(map[it]?.map { ItemWrapper(it) } ?: emptyList())
        }
        return result
    }
}