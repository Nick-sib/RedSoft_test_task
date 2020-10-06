package com.nickolay.redsofttesttask.data.model

sealed class ProductsResult {
    class Success<out T>(val data: T): ProductsResult()
    class Error(val error: Throwable): ProductsResult()
}
