package com.nickolay.redsofttesttask.data.entity

import java.util.*

data class Product(
    val id: Int = 0,
    val title: String = "",
    val short_description: String = "",
    val image_url: String = "",
    val amount: Int = 0,
    val price: Float = 0f,
    val producer: String = "",
    val categories: Array<Category>) {

    override fun equals(other: Any?) =
        when{
            (this === other) -> true
            (javaClass != other?.javaClass) -> false
            (id != (other as Product).id) -> false
            else -> true
        }

    override fun hashCode() = id.hashCode()
}