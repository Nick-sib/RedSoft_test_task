package com.nickolay.redsofttesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nickolay.redsofttesttask.data.entity.Product
import com.nickolay.redsofttesttask.data.provider.ThreadProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ThreadProvider.getAllData()
    }


    companion object {
        fun showme(value: List<Product>){
            Log.d("myLOG", "Size = ${value.size}")
        }
    }
}