package com.accme.timepad.viewModels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accme.timepad.model.Task
import com.accme.timepad.persistence.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks get() = _tasks.asStateFlow()
    var isLoading by mutableStateOf(true)

    fun loadTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            repository
                .getAll()
                .collect {
                    _tasks.value = it
                    isLoading = false
                }
        }
    }
}
