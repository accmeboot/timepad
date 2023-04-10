package com.accme.timepad.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accme.timepad.model.Task
import com.accme.timepad.persistence.TaskRepository
import com.accme.timepad.utils.TaskCountDown
import com.accme.timepad.utils.getNiceCountTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

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
            val hours = it.hour.hours
            val minutes = it.minute.minutes

            val duration = Duration.parseIsoString((hours + minutes).toIsoString())

            _countdown = TaskCountDown(
                duration = duration.inWholeMilliseconds,
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
        val duration = Duration.parseIsoString((remaining.seconds).toIsoString())
        val time = LocalTime.ofSecondOfDay(duration.inWholeSeconds)

        currentCount.value = time.getNiceCountTime()
    }
}
