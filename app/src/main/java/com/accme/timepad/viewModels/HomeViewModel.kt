package com.accme.timepad.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accme.timepad.data.Task
import com.accme.timepad.persistence.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    val tasks = MutableStateFlow<List<Task>>(emptyList())

    fun loadTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getAll()
                .collect {
                    tasks.value = it
                }
        }
    }
}