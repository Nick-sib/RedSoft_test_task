package com.nickolay.redsofttesttask.data.provider

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.nickolay.redsofttesttask.MainActivity
import com.nickolay.redsofttesttask.data.entity.Products
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

object ThreadProvider: DataProvider {

    private const val TIMEOUT = 10000
    private const val METHOD = "GET"

    private fun doRequest(url: URL) {
        //Можно сделать через Retrofit или OkHttp, о нестоит плодить зависимости где это решается в одну функцию
        Thread {
            val connection: HttpURLConnection = (url.openConnection() as HttpURLConnection).apply{
                requestMethod = METHOD
                connectTimeout = TIMEOUT
            }
            try {
                val inStream = BufferedReader(InputStreamReader(connection.inputStream))
                val result = inStream.readLine()
                Handler(Looper.getMainLooper()).post {
                    val r = Gson().fromJson(
                        result,
                        Products::class.java
                    )
                    MainActivity.showData(r.data)
                }
            }  catch (e: Exception) {
                //TODO: interface error reporting
            }
            finally {
                connection.disconnect()
            }
        }.start()
    }

    override fun getAllData() {
        //TODO: Check connection, if have not internet show "no intenet view"
        doRequest(URL("https://rstestapi.redsoftdigital.com/api/v1/products"))
    }
}