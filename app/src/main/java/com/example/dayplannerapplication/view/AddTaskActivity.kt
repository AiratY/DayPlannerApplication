package com.example.dayplannerapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.dayplannerapplication.MESSAGE_ERROR_EDIT_TEXT
import com.example.dayplannerapplication.R
import com.example.dayplannerapplication.TASK_ID
import com.example.dayplannerapplication.presenter.AddTaskPresenter
import com.example.dayplannerapplication.view.models.dataTask
import java.util.Date

class AddTaskActivity : AppCompatActivity(), AddTaskContractView {

    private lateinit var nameEditView: EditText
    private lateinit var descEditView: EditText
    private lateinit var presenter: AddTaskPresenter
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        init()
    }
    private fun init() {
        nameEditView = findViewById(R.id.nameTaskEditText)
        descEditView = findViewById(R.id.descTaskEditText)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)

        presenter = AddTaskPresenter()
        presenter.attachView(this)
        findViewById<Button>(R.id.saveTaskButton).setOnClickListener {
            presenter.save()
        }
    }

    override fun getData() {
        val name = nameEditView.text.toString()
        val desc = descEditView.text.toString()
        val year = datePicker.year - 1900
        val month = datePicker.month
        val day = datePicker.dayOfMonth
        val hour = timePicker.hour
        val minutes = timePicker.minute
        val dataTask = dataTask(Date(year, month, day, hour, minutes), Date(year, month, day, hour + 1, minutes), name = name, description = desc)
        presenter.setData(dataTask)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.viewDestroy()
    }

    override fun showMessageNullName() {
        nameEditView.hint = MESSAGE_ERROR_EDIT_TEXT
    }

    override fun moveToMain() {
        startActivity(Intent(applicationContext, MainActivity()::class.java))
    }

    override fun getContext(): Context = applicationContext

    override fun checkId(): Int {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            return bundle.getInt(TASK_ID)
        }
        return -1
    }

    override fun loudeTask(
        name: String,
        description: String,
        year: Int,
        month: Int,
        day: Int,
        hours: Int,
        min: Int
    ) {
        nameEditView.setText(name)
        descEditView.setText(description)
        datePicker.init(year, month, day, null)
        timePicker.hour = hours
        timePicker.minute = min
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            this.finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
