package com.GOSU1902.listmaker.ui.main.ui.listdetail

import androidx.lifecycle.ViewModel
import com.GOSU1902.listmaker.TaskList

class ListDetailViewModel() : ViewModel() {
    lateinit var onTaskAdded: (() -> Unit)
    lateinit var list: TaskList

    fun addTask(task: String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }

}