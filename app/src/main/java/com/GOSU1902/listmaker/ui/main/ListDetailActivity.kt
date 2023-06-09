package com.GOSU1902.listmaker.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.GOSU1902.listmaker.MainActivity
import com.GOSU1902.listmaker.R
import com.GOSU1902.listmaker.databinding.ActivityListDetailBinding
import com.GOSU1902.listmaker.ui.main.ui.listdetail.ListDetailFragment
import com.GOSU1902.listmaker.ui.main.ui.listdetail.ListDetailViewModel

class ListDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityListDetailBinding
    lateinit var viewModel: ListDetailViewModel
    lateinit var fragment: ListDetailFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        binding.addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
        title = viewModel.list.name
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, viewModel.list)
        //val intent = Intent()
        //intent.putExtra(MainActivity.INTENT_LIST_KEY, viewModel.list)
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

    private fun showCreateTaskDialog() {
        //1
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        //2
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                // 3
                val task = taskEditText.text.toString()
                // 4
                viewModel.addTask(task)
                //5
                dialog.dismiss()
            }
            //6
            .create()
            .show()
    }
}