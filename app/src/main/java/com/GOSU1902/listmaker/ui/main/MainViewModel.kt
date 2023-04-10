package com.GOSU1902.listmaker.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.GOSU1902.listmaker.TaskList

class MainViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    lateinit var onListAdded: (() -> Unit)
    val lists: MutableList<TaskList> by lazy {
        retrieveLists()
    }

    private fun retrieveLists(): MutableList<TaskList> {
        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()
        for (taskList in sharedPreferencesContents) {
            val itemHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemHashSet)
            taskLists.add(list)
        }
        return taskLists
    }

    fun saveList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }

    fun updateList(list: TaskList) {
        sharedPreferences.edit().putStringSet(
            list.name,
            list.tasks.toHashSet()
        ).apply()
        lists.add(list)
    }

    fun refreshLists() {
        lists.clear()
        lists.addAll(retrieveLists())
    }
}

class MainViewModelFactory(private val sharedPreferences: SharedPreferences) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(sharedPreferences) as T
    }
}