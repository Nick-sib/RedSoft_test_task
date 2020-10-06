package com.nickolay.redsofttesttask.data

import com.nickolay.redsofttesttask.data.provider.DataProvider

class ProductsRepository(val dataProvider: DataProvider) {

    fun getNotes() = dataProvider.subscribeToAllProducts()

}