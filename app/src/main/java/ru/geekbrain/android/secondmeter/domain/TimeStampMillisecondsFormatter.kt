package ru.geekbrain.android.secondmeter.domain


class TimeStampMillisecondsFormatter {
    companion object{
        const val DEFAULT_TIME = "00:00:000"
    }

    fun format(timeStamp: Long) : String{
        val millisecondsFormatted = (timeStamp % 1000).pad(3)
        val secondsFormatted = ((timeStamp/1000)%60).pad(2)
        val minutesFormatted = ((timeStamp/60000)%60).pad(2)
        val hoursFormatted = (timeStamp/3600000).pad(2)
        return "$hoursFormatted:$minutesFormatted:$secondsFormatted"
    }
}

private fun Long.pad(desiredLength: Int) =
    this.toString().padStart(desiredLength, '0')
