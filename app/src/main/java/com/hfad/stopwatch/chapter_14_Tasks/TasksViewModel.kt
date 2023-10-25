package com.hfad.stopwatch.chapter_14_Tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao) : ViewModel() {

    var newTaskName = ""
    val tasks = dao.getAll()

    /*    val taskString = Transformations.map(tasks) {
            tasks -> formatTasks(tasks)
        }*/
    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask

    fun addTask() {
        viewModelScope.launch {
            val task = Tasks()
            task.tasksName = newTaskName
            dao.insert(task)
        }
    }

    fun onTaskClicked(taskId: Long) {
        _navigateToTask.value = taskId
    }

    fun onTaskNavigation() {
        _navigateToTask.value = null
    }

}

/*private fun formatTask(task: Tasks): String {
        var str = "ID: ${task.tasksId}"
        str += '\n' + "Name: ${task.tasksName}"
        str += '\n' + "Complete: ${task.tasksDone}" + '\n'
        return str
    }

     private fun formatTasks(tasks: List<Tasks>): String {
        return tasks.fold(""){
            str, item -> str + '\n' +formatTask(item)
        }
    }



    }*/