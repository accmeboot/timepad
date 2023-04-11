package com.accme.timepad.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accme.timepad.model.Task
import com.accme.timepad.persistence.TaskRepository
import com.accme.timepad.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    private var _task: MutableStateFlow<Task>? = null
    private var _countdown: TaskCountDown? = null
    val task get() = _task?.asStateFlow()
    val currentCount = MutableStateFlow("")
    val isCounting = mutableStateOf(false)

    fun loadTask(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getById(id)
                .collect {
                    _task = MutableStateFlow(it)
                    currentCount.value = it.duration.getNiceCountTime()
                }
        }

    }

    fun startCountDown() {
        task?.value?.duration?.let {
            _countdown = TaskCountDown(
                duration = it.toDuration().inWholeMilliseconds,
                onFinished = { onCountDownFinished() },
                onTicked = { remaining -> onCountDownTicked(remaining) }
            )

            _countdown?.start()
            isCounting.value = true
        }
    }

    fun stopCountDown() {
        _countdown?.cancel()

        _countdown = null
        isCounting.value = false

        task?.value?.duration?.let {
            currentCount.value = it.getNiceCountTime()
        }
    }

    private fun onCountDownFinished() {
        isCounting.value = false
    }

    private fun onCountDownTicked(remaining: Long) {
        currentCount.value = remaining.toLocalTime().getNiceCountTime()
    }
}
