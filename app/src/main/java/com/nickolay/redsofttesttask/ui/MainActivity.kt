package com.nickolay.redsofttesttask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nickolay.redsofttesttask.R
import com.nickolay.redsofttesttask.data.entity.Product
import com.nickolay.redsofttesttask.data.provider.ThreadProvider
import com.nickolay.redsofttesttask.ui.adapter.ProductsRVAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //val viewModel: MainViewModel<List<Product>> = MainViewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ThreadProvider.getAllData()

        ititView()
    }

    fun ititView(){


        rv_products.adapter = adapter



    }



    companion object {
        fun showData(value: List<Product>){
            adapter.products = value
        }
        val adapter = ProductsRVAdapter{
            Log.d("myLOG", "click ${it.title}")
        }

    }
}