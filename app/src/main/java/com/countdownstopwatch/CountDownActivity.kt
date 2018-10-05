package com.countdownstopwatch

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.countdownstopwatch.databinding.ActivityCountdownBinding
import androidx.databinding.adapters.TextViewBindingAdapter.setText


class CountDownActivity : AppCompatActivity() {

    private lateinit var bind: ActivityCountdownBinding
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_countdown)
        bind.start.setOnClickListener { startOnClick(bind.start) }
        bind.stopReset.setOnClickListener { stopOnClick(bind.stopReset) }

    }

    private fun startOnClick(view: View) {
        timer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millSecondsLeftToFinish: Long) {
                val time = (millSecondsLeftToFinish / 1000).toString()
                bind.textView.text = time
            }

            override fun onFinish() {
                bind.textView.text = "Done!"
            }
        }

    }

    private fun stopOnClick(view: View) {
        timer.cancel()
        bind.textView.text = "0"
    }
}