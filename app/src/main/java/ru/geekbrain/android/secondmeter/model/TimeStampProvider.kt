package ru.geekbrain.android.secondmeter.model

interface TimeStampProvider {
    fun getMilliseconds(): Long
}