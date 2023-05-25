package ru.geekbrain.android.secondmeter.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.geekbrain.android.secondmeter.databinding.ActivityMainBinding
import ru.geekbrain.android.secondmeter.viewmodel.StopMeterViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val secundometers = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: StopMeterViewModel by viewModels { StopMeterViewModelFactory(secundometers) }

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            launch {
                viewModel.stopwatchListOrchestrator.tickerList[0].collect() {
                    binding.textTime1.text = viewModel.stopwatchListOrchestrator.tickerList[0].value
                }
            }

            launch {
                viewModel.stopwatchListOrchestrator.tickerList[1].collect {
                    binding.textTime2.text = it
                }
            }
        }

        binding.buttonStart1.setOnClickListener {
            viewModel.stopwatchListOrchestrator.start(0)
        }

        binding.buttonPause1.setOnClickListener {
            viewModel.stopwatchListOrchestrator.pause(0)
        }

        binding.buttonStop1.setOnClickListener {
            viewModel.stopwatchListOrchestrator.stop(0)
        }

        binding.buttonStart2.setOnClickListener {
            viewModel.stopwatchListOrchestrator.start(1)
        }

        binding.buttonPause2.setOnClickListener {
            viewModel.stopwatchListOrchestrator.pause(1)
        }

        binding.buttonStop2.setOnClickListener {
            viewModel.stopwatchListOrchestrator.stop(1)
        }
    }
}