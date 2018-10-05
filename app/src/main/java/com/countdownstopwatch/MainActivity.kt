package com.countdownstopwatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.countdownstopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bind.btCd.setOnClickListener { navigateToCountDown() }
        bind.btSw.setOnClickListener { navigateToStopWatch() }
    }

    private fun navigateToCountDown() {
        startActivity(Intent(this, CountDownActivity::class.java))
    }

    private fun navigateToStopWatch() {
        startActivity(Intent(this, StopWatchActivity::class.java))
    }
}
