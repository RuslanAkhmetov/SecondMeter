package ru.geekbrain.android.secondmeter.domain

class StopWatchStateHolder(
    private val stopWatchStateCalculator: StopWatchStateCalculator,
    private val elapsedTimeStateCalculator: ElapsedTimeStateCalculator,
    private val timeStampMillisecondsFormatter: TimeStampMillisecondsFormatter
) {
    var currentState: StopWatchState = StopWatchState.Paused(0)
        private set

    fun start() {
        currentState = stopWatchStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = stopWatchStateCalculator.calculatePausedState(currentState)
    }

    fun stop() {
        currentState = StopWatchState.Paused(0)
    }

    fun getStringTimePresentation(): String{
        val elapsedTime =
            when(val _currentState = currentState){
                is StopWatchState.Paused -> _currentState.elapsedTime
                is StopWatchState.Running -> elapsedTimeStateCalculator.calculate(_currentState )
            }
        return timeStampMillisecondsFormatter.format(elapsedTime)
    }


}