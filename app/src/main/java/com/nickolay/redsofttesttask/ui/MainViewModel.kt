package com.nickolay.redsofttesttask.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.nickolay.redsofttesttask.data.ProductsRepository
import com.nickolay.redsofttesttask.data.entity.Product
import com.nickolay.redsofttesttask.data.entity.Products
import com.nickolay.redsofttesttask.data.model.ProductsResult
import com.nickolay.redsofttesttask.ui.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

class MainViewModel(productsRepository: ProductsRepository) : BaseViewModel<List<Product>?>() {

    private val productsChanel = productsRepository.getProducts()

    init {
        launch {
            productsChanel.consumeEach {
                when (it) {
                    is ProductsResult.Success<*> -> setData(it.data as? List<Product>)
                    is ProductsResult.Error -> setError(it.error)
                }
            }
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        productsChanel.cancel()
    }
}