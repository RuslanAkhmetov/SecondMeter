package ru.geekbrain.android.secondmeter.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.geekbrain.android.secondmeter.domain.*
import ru.geekbrain.android.secondmeter.model.TimeStampProvider

class StopMeterViewModel: ViewModel() {


    private val timeStampProvider = object : TimeStampProvider {
        override fun getMilliseconds(): Long =
            System.currentTimeMillis()
    }

    val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopWatchStateHolder(
            StopWatchStateCalculator(
                this.timeStampProvider,
                ElapsedTimeStateCalculator(this.timeStampProvider)
            ),
            ElapsedTimeStateCalculator(timeStampProvider),
            TimeStampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        )
    )



}