package com.vanitygoods.tudu.ui.createtodotask.view

import android.app.Dialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.TimePicker
import com.vanitygoods.tudu.ui.createtodotask.viewmodel.CreateTaskViewModel
import com.vanitygoods.tudu.ui.createtodotask.viewmodel.CreateTaskViewModelContract
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var viewModel: CreateTaskViewModelContract

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        viewModel.onTimeSet(hourOfDay, minute)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(CreateTaskViewModel::class.java)
            val time = viewModel.time.value ?: LocalTime.now(ZoneId.systemDefault())
            return TimePickerDialog(activity, this, time.hour, time.minute, DateFormat.is24HourFormat(it))
        }
        return super.onCreateDialog(savedInstanceState)
    }
}