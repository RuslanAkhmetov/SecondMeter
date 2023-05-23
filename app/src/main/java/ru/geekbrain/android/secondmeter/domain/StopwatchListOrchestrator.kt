package ru.geekbrain.android.secondmeter.domain

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class StopwatchListOrchestrator(
    private val stopWatchStateHolder: StopWatchStateHolder,
    private val scope: CoroutineScope
) {
    private var job: Job? = null
    private val mutableTicker = MutableStateFlow(stopWatchStateHolder.getStringTimePresentation())
    val ticker = mutableTicker

    fun start(){
        if (job == null) {
            startJob()
        }
        stopWatchStateHolder.start()
    }

    fun pause(){
        stopWatchStateHolder.pause()
        stopJob()
    }

    fun stop(){
        clearValue()
        stopWatchStateHolder.stop()
        stopJob()
    }


    private fun startJob() {
        scope.launch {
            while (isActive){
                mutableTicker.value = stopWatchStateHolder.getStringTimePresentation()
                delay(20)
            }
        }
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = stopWatchStateHolder.getStringTimePresentation()
    }

}