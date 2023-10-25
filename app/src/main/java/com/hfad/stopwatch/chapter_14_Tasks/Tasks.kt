package com.hfad.stopwatch.chapter_14_Tasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Tasks (
    @PrimaryKey(autoGenerate = true)
    var tasksId: Long = 0L,

    @ColumnInfo(name = "task_name")
    var tasksName: String = "",

    @ColumnInfo(name = "task_done")
    var tasksDone: Boolean = false
) {

}