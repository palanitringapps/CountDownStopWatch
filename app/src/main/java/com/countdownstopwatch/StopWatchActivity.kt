package com.countdownstopwatch

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.countdownstopwatch.databinding.ActivityStopwatchBinding


class StopWatchActivity : AppCompatActivity() {

    lateinit var bind: ActivityStopwatchBinding
    var startTime = 0L
    val customHandler = Handler()
    var timeInMilliseconds = 0L
    var timeSwapBuff = 0L
    var updatedTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_stopwatch)
        bind.btStart.setOnClickListener { startTimer() }
        bind.btPause.setOnClickListener { resetTimer() }
        bind.btStop.setOnClickListener { stopTimer() }
    }

    private fun resetTimer() {
        bind.timerValue.setText(String.format("%02d", 0) + ":"
                + String.format("%02d", 0) + ":"
                + String.format("%03d", 0))
        startTime = SystemClock.uptimeMillis()
        timeSwapBuff = 0
    }

    private fun startTimer() {
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0)
    }

    private fun stopTimer() {
        customHandler.removeCallbacks(updateTimerThread)
    }


    var updateTimerThread: Runnable = Runnable {
        UpdateTimerThread() }


    inner class UpdateTimerThread : Runnable {
        override fun run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime
            updatedTime = timeSwapBuff + timeInMilliseconds

            var seconds = (updatedTime / 1000) as Int
            val minutes = seconds / 60
            seconds = seconds % 60
            val milliseconds = (updatedTime % 1000) as Int

            var string = ""
            string += "" + String.format("%02d", minutes)
            string += ":" + String.format("%02d", seconds)
            string += ":" + String.format("%03d", milliseconds)

            bind.timerValue.setText(string)
            customHandler.postDelayed(this, 0)
        }
    }
}