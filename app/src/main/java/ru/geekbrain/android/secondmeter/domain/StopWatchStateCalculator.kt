package ru.geekbrain.android.secondmeter.domain

import ru.geekbrain.android.secondmeter.model.TimeStampProvider

class StopWatchStateCalculator(
    private val timeStampProvider: TimeStampProvider,
    private val elapsedTimeCalculator: ElapsedTimeStateCalculator
) {

    fun calculateRunningState(oldState: StopWatchState): StopWatchState.Running =
        when(oldState){
            is StopWatchState.Running -> oldState
            is StopWatchState.Paused -> {
                StopWatchState.Running(
                    startTime = timeStampProvider.getMilliseconds(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: StopWatchState): StopWatchState.Paused =
        when(oldState){
            is StopWatchState.Paused -> oldState
            is StopWatchState.Running ->
                StopWatchState.Paused(
                    elapsedTime = elapsedTimeCalculator.calculate(oldState)
                )
        }
}