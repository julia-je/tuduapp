package com.vanitygoods.tudu.ui.createtodotask.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import com.vanitygoods.tudu.R
import com.vanitygoods.tudu.ui.createtodotask.viewmodel.CreateTaskViewModel
import com.vanitygoods.tudu.ui.createtodotask.viewmodel.CreateTaskViewModelContract
import com.vanitygoods.tudu.util.DateTimeFormats
import kotlinx.android.synthetic.main.activity_create_task.*

private const val DATE_PICKER_TAG = "datePicker"
private const val CONFIRMATION_TAG = "confirmation"
private const val TIME_PICKER = "timePicker"

class CreateTaskActivity : AppCompatActivity() {
    private lateinit var viewModel: CreateTaskViewModelContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
        viewModel = ViewModelProviders.of(this).get(CreateTaskViewModel::class.java)

        viewModel.finishActivity.observe(this, Observer { finish ->
            if (finish == true) finish()
        })

        viewModel.dateString.observe(this, Observer {
            it?.let { date ->
                add_date.hint = date.format(DateTimeFormats.dateFormat)
            }
        })

        viewModel.timeString.observe(this, Observer {
            it?.let { time ->
                add_time.hint = time.format(DateTimeFormats.timeFormat)
            }
        })

        viewModel.showDatePicker.observe(this, Observer { show ->
            if (show == true) {
                showDatePickerDialog()
            }
        })

        viewModel.showTimePicker.observe(this, Observer { show ->
            if (show == true) {
                showTimePickerDialog()
            }
        })

        viewModel.showConfirmationDialog.observe(this, Observer { show ->
            if (show == true) {
                showConfirmationDialog()
            }
        })

        viewModel.dismissConfirmationDialog.observe(this, Observer { hide ->
            if (hide == true) {
                val dialogFragment = supportFragmentManager.findFragmentByTag(CONFIRMATION_TAG) as? DialogFragment
                dialogFragment?.dismiss()
            }
        })
        task_title.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onTitleChanged(s.toString())
            }
        })

        summary.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onSummaryChanged(s.toString())
            }
        })

        add_date.setOnClickListener {
            viewModel.onDateClicked()
        }

        add_time.setOnClickListener {
            viewModel.onTimeClicked()
        }
        check_box.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCheckBoxClicked(isChecked)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_task_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.done         -> {
                viewModel.createTask()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else              -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDatePickerDialog() {
        DatePickerFragment().show(supportFragmentManager, DATE_PICKER_TAG)
    }

    private fun showTimePickerDialog() {
        TimePickerFragment().show(supportFragmentManager, TIME_PICKER)
    }

    private fun showConfirmationDialog() {
        ConfirmationDialog().show(supportFragmentManager, CONFIRMATION_TAG)
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }
}

abstract class SimpleTextWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //no-op
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //no-op
    }

}
