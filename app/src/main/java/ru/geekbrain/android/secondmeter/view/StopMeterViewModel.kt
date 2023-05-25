package ru.geekbrain.android.secondmeter.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.geekbrain.android.secondmeter.domain.*
import ru.geekbrain.android.secondmeter.model.TimeStampProvider

class StopMeterViewModel(private val amountOfStopWatchers: Int) : ViewModel() {


    private val timeStampProvider = object : TimeStampProvider {
        override fun getMilliseconds(): Long =
            System.currentTimeMillis()
    }



    private val stopWatchStateHolderList= List<StopWatchStateHolder> (amountOfStopWatchers){
            StopWatchStateHolder(
                StopWatchStateCalculator(
                    this.timeStampProvider,
                    ElapsedTimeStateCalculator(this.timeStampProvider)
                ),
                ElapsedTimeStateCalculator(this.timeStampProvider),
                TimeStampMillisecondsFormatter()
            )
        }

    val stopwatchListOrchestrator = StopwatchListOrchestrator(
        stopWatchStateHolderList,
        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        )
    )


}