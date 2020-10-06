package com.nickolay.redsofttesttask.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

class MainViewModel<S>: ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default + Job()
    }

    private val productChannel = BroadcastChannel<S>(CONFLATED)
    fun getViewState(): ReceiveChannel<S> = productChannel.openSubscription()



    public override fun onCleared() {
        super.onCleared()
        productChannel.cancel()
    }
}