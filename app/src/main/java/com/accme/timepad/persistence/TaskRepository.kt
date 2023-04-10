package com.accme.timepad.persistence

import com.accme.timepad.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TaskRepository @Inject constructor(private val dao: TaskDao) {
    suspend fun createTask(task: Task) = dao.create(task)
    // suspend fun updateTask(task: Task) = dao.update(task)
    // suspend fun deleteTask(task: Task) = dao.delete(task)

    fun getAll(): Flow<List<Task>> =
        dao.all().flowOn(Dispatchers.IO).conflate()

    fun getById(id: Int): Flow<Task> =
        dao.getById(id).flowOn(Dispatchers.IO).conflate()
}
