package com.nickolay.redsofttesttask.data.provider

import com.nickolay.redsofttesttask.data.model.ProductsResult
import kotlinx.coroutines.channels.ReceiveChannel

interface DataProvider {
    fun subscribeToAllProducts() : ReceiveChannel<ProductsResult>
}