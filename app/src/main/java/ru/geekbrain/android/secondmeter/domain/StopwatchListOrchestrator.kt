package ru.geekbrain.android.secondmeter.domain

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StopwatchListOrchestrator(
    private val stopWatchStateHolderList: List<StopWatchStateHolder>,
    private val scope: CoroutineScope
) {
    private var jobList = arrayOfNulls<Job>(stopWatchStateHolderList.size)

    private val mutableTickerList = stopWatchStateHolderList.map{
        MutableStateFlow(it.getStringTimePresentation())
    }

    val tickerList = mutableTickerList as List<StateFlow<String>>

    fun start(jobNum : Int){
        if (jobNum >=0 && jobNum< jobList.size ) {
            if (jobList[jobNum] == null) {
                startJob(jobNum)
            }
            stopWatchStateHolderList[jobNum].start()
        }
    }

    fun pause(jobNum : Int){
        stopWatchStateHolderList[jobNum].pause()
        stopJob(jobNum)
    }

    fun stop(jobNum : Int){
        clearValue(jobNum)
        stopWatchStateHolderList[jobNum].stop()
        stopJob(jobNum)
    }


    private fun startJob(jobNum: Int) {
        scope.launch {
            while (isActive){
                mutableTickerList[jobNum].value =
                    stopWatchStateHolderList[jobNum].getStringTimePresentation()
                delay(50)
            }
        }
    }

    private fun stopJob(jobNum: Int) {
        jobList[jobNum] = null

    }

    private fun clearValue(jobNum: Int) {
        mutableTickerList[jobNum].value =
            stopWatchStateHolderList[jobNum].getStringTimePresentation()
    }

}