package com.nickolay.redsofttesttask

import androidx.lifecycle.ViewModel
import com.nickolay.redsofttesttask.data.entity.Product
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
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