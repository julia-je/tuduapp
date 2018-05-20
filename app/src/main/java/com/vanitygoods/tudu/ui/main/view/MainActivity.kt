package com.vanitygoods.tudu.ui.main.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vanitygoods.tudu.R
import com.vanitygoods.tudu.ui.createtodotask.view.CreateTaskActivity
import com.vanitygoods.tudu.ui.main.viewmodel.ToDoListViewModel
import com.vanitygoods.tudu.ui.main.viewmodel.ToDoListViewModelContract
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: ToDoListViewModelContract = ViewModelProviders.of(this).get(ToDoListViewModel::class.java)
        val adapter = TodoAdapter(viewModel)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        viewModel.listOfTasks.observe(this, Observer {
            it?.let {
                adapter.updateList(it)
            }
        })

        viewModel.showEmptyView.observe(this, Observer {
            empty_view.visibility = if (it == true) View.VISIBLE else View.GONE
        })

        add.setOnClickListener {
            startActivity(Intent(this, CreateTaskActivity::class.java))
        }
    }
}
