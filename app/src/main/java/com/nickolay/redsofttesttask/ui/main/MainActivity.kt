package com.nickolay.redsofttesttask.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.nickolay.redsofttesttask.R
import com.nickolay.redsofttesttask.data.ProductsRepository
import com.nickolay.redsofttesttask.data.entity.Product
import com.nickolay.redsofttesttask.data.provider.ThreadProvider
import com.nickolay.redsofttesttask.ui.adapter.ProductsRVAdapter
import com.nickolay.redsofttesttask.ui.base.BaseActivity
import com.nickolay.redsofttesttask.ui.product.ProductActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Product>?>() {

    override val viewModel: MainViewModel by lazy {
        MainViewModel(ProductsRepository(ThreadProvider()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv_products.adapter = ProductsRVAdapter(
            onCharClick = {
                Log.d("myLOG", "doChar: $it")
            },
            onItemClick =  {
                ProductActivity.start(this, it.id)
            }
        )

    }

    override fun renderData(data: List<Product>?) {
        data?.let {
            (rv_products.adapter as ProductsRVAdapter).products = it
        }
    }



    companion object {


    }
}