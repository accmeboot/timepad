package com.accme.timepad.persistence

import com.accme.timepad.data.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskRepository @Inject constructor(private val dao: TaskDao) {
    suspend fun createTask(task: Task) = dao.create(task)
    suspend fun updateTask(task: Task) = dao.update(task)
    suspend fun deleteTask(task: Task) = dao.delete(task)

    fun getAll(): Flow<List<Task>> = dao.all().flowOn(Dispatchers.IO)
        .conflate()
}