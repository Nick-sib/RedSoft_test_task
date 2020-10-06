package com.nickolay.redsofttesttask.data.provider

import com.google.gson.Gson
import com.nickolay.redsofttesttask.data.entity.Products
import com.nickolay.redsofttesttask.data.model.ProductsResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ThreadProvider: DataProvider {

    private val URl = URL("https://rstestapi.redsoftdigital.com/api/v1/products")
    private val TIMEOUT = 10000
    private val METHOD = "GET"

    override fun subscribeToAllProducts() : ReceiveChannel<ProductsResult> = Channel<ProductsResult>(Channel.CONFLATED).apply {
        //TODO: Check connection, if have not internet show "no intenet view"

        Thread {
            val connection: HttpURLConnection = (URl.openConnection() as HttpURLConnection).apply{
                requestMethod = METHOD
                connectTimeout = TIMEOUT
            }
            try {
                val inStream = BufferedReader(InputStreamReader(connection.inputStream))
                val result = inStream.readLine()
                val value = result?.let {
                    val jsonData = Gson().fromJson(it, Products::class.java)
                    ProductsResult.Success(jsonData.data)
                } ?: {
                    ProductsResult.Error(Throwable("EMPTY RESULT"))
                }
                value?.let {
                    offer(it as ProductsResult)
                }
            }  catch (e: Exception) {
                offer(ProductsResult.Error(e))
            }
            finally {
                connection.disconnect()
            }
        }.start()
    }

    /*Handler(Looper.getMainLooper()).post {
                    val r = Gson().fromJson(
                        result,
                        Products::class.java
                    )
                    MainActivity.showData(r.data)
                }*/
}