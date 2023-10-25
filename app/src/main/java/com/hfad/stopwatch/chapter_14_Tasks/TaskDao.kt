package com.hfad.stopwatch.chapter_14_Tasks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(tasks: Tasks)

    @Update
    suspend fun update(tasks: Tasks)

    @Delete
    suspend fun delete(tasks: Tasks)

    @Query("SELECT * FROM task_table WHERE tasksId = :tasksId")
    fun get(tasksId: Long ): LiveData<Tasks>

    @Query("SELECT * FROM task_table ORDER BY tasksId DESC")
    fun getAll(): LiveData<List<Tasks>>
}