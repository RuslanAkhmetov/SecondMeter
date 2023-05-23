package ru.geekbrain.android.secondmeter.domain

import ru.geekbrain.android.secondmeter.model.TimeStampProvider

class ElapsedTimeStateCalculator(
    private val timeStampProvider: TimeStampProvider
) {
    fun calculate(state: StopWatchState.Running): Long =
        if ( timeStampProvider.getMilliseconds() > state.startTime){
            timeStampProvider.getMilliseconds() - state.startTime + state.elapsedTime
        } else {
            state.elapsedTime
        }
}
