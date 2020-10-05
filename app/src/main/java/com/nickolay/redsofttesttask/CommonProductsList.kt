package com.nickolay.redsofttesttask

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

object CommonProductsList {
    fun getData() {
        //TODO: Check connection, if have not internet show "no intenet view"
        doRequest(URL("https://rstestapi.redsoftdigital.com/api/v1/products"))
    }

    private fun doRequest(url: URL) {

        Thread {
            try {
                val connection: HttpURLConnection = (url.openConnection() as (HttpURLConnection)).apply{
                    requestMethod = "GET"
                    connectTimeout = 10000
                }
                val inStream = BufferedReader(InputStreamReader(connection.inputStream))
                val result = inStream.readLine()
                Log.d("myLOG", result)
            }  catch (e: Exception) {

            }
        }.start()

    }
}