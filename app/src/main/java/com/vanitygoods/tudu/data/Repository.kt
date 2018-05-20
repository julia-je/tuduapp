package com.vanitygoods.tudu.data

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId


class Repository(context: Context) {
    private val taskDao = DbAccess.getTodoDao(context)

    fun createTodoTask(todoTask: TodoTask, onSuccessFunction: () -> Unit) {
        Flowable.just(taskDao)
            .subscribeOn(Schedulers.io())
            .doOnNext { it.insert(todoTask) }
            .observeOn(AndroidSchedulers.mainThread())
            .ignoreElements()
            .subscribe(onSuccessFunction, { it.printStackTrace() })
    }

    fun updateDoneStatus(isDone: Boolean, taskId: Long) {
        Flowable.just(taskDao)
            .subscribeOn(Schedulers.io())
            .doOnNext { it.setDone(isDone, taskId, LocalDateTime.now(ZoneId.systemDefault())) }
            .ignoreElements()
            .subscribe({}, { it.printStackTrace() })
    }

    fun getListOfTasks() = taskDao.getAll()
}