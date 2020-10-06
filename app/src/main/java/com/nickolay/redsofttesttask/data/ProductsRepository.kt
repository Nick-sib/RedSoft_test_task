package com.nickolay.redsofttesttask.data

import com.nickolay.redsofttesttask.data.provider.DataProvider

class ProductsRepository(private val dataProvider: DataProvider) {

    fun getProducts() = dataProvider.subscribeToAllProducts()

}