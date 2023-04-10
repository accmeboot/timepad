package com.accme.timepad.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accme.timepad.model.Task
import com.accme.timepad.persistence.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class PadViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    fun createTask(
        name: String,
        duration: LocalTime,
        date: LocalDateTime
    ) {
        viewModelScope.launch {
            repository.createTask(Task(name = name, date = date, duration = duration))
        }
    }
}
