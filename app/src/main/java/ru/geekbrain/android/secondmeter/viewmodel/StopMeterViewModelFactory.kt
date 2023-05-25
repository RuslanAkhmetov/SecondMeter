package ru.geekbrain.android.secondmeter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.geekbrain.android.secondmeter.view.StopMeterViewModel

class StopMeterViewModelFactory(private val stopwatcherAmount: Int)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return StopMeterViewModel(stopwatcherAmount) as T
    }
}