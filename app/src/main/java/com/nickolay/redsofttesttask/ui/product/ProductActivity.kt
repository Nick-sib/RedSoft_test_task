package com.nickolay.redsofttesttask.ui.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nickolay.redsofttesttask.R
import com.nickolay.redsofttesttask.data.entity.Product
import com.nickolay.redsofttesttask.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity: BaseActivity<Product>() {

    override val viewModel: ProductViewModel by lazy {
        ProductViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //toolbar.title = "Product 1"
    }

    override fun renderData(data: Product) {

    }

    companion object {
        private val EXTRA_NOTE = ProductActivity::class.java.name + "extra.NOTE"

        fun start(context: Context, productId: Int? = null): Intent = Intent(context, ProductActivity::class.java).apply {
            putExtra(EXTRA_NOTE, productId)
            context.startActivity(this)
        }
    }
}