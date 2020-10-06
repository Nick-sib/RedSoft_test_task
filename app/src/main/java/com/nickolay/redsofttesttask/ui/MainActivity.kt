package com.nickolay.redsofttesttask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nickolay.redsofttesttask.R
import com.nickolay.redsofttesttask.data.ProductsRepository
import com.nickolay.redsofttesttask.data.entity.Product
import com.nickolay.redsofttesttask.data.provider.DataProvider
import com.nickolay.redsofttesttask.data.provider.ThreadProvider
import com.nickolay.redsofttesttask.ui.adapter.ProductsRVAdapter
import com.nickolay.redsofttesttask.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Product>?>() {

    override val viewModel: MainViewModel by lazy {
        MainViewModel(ProductsRepository(ThreadProvider()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv_products.adapter = ProductsRVAdapter{
            Log.d("myLOG", "onCreate: ${it.id}")
        }

    }

    override fun renderData(data: List<Product>?) {
        data?.let {
            (rv_products.adapter as ProductsRVAdapter).products = it
        }
    }



    companion object {

    }
}