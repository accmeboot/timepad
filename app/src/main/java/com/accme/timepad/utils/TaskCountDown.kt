package com.accme.timepad.utils

import android.os.CountDownTimer

class TaskCountDown(
    val duration: Long,
    val onFinished: () -> Unit,
    val onTicked: (Long) -> Unit
) : CountDownTimer(duration, 1000)  {
    override fun onTick(p0: Long) {
        onTicked(p0 / 1000)
    }

    override fun onFinish() {
        onFinished()
    }
}
