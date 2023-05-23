package ru.geekbrain.android.secondmeter.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.geekbrain.android.secondmeter.R

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel:StopMeterViewModel by viewModels()

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            viewModel.stopwatchListOrchestrator.ticker.collect{
                findViewById<TextView>(R.id.text_time).text = it
            }
        }

        findViewById<Button>(R.id.button_start).setOnClickListener{
            viewModel.stopwatchListOrchestrator.start()
        }

        findViewById<Button>(R.id.button_pause).setOnClickListener{
            viewModel.stopwatchListOrchestrator.pause()
        }

        findViewById<Button>(R.id.button_stop).setOnClickListener{
            viewModel.stopwatchListOrchestrator.stop()
        }
    }
}