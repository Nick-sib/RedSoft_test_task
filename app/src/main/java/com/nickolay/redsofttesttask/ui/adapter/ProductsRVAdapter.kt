package com.nickolay.redsofttesttask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nickolay.redsofttesttask.R
import com.nickolay.redsofttesttask.data.entity.Product
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsRVAdapter (val onItemClick: ((Product) -> Unit)? = null, val onCharClick: ((Int) -> Unit)? = null) : RecyclerView.Adapter<ProductsRVAdapter.ViewHolder>() {

    var products: List<Product> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(products[position])

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(product: Product) {

            Picasso.get()
                .load(product.image_url)
                .error(R.drawable.ic_image_24)
                .fit()
                .into(containerView.iv_image)

            containerView.tv_product_category.text =
                if (product.categories.isNotEmpty()) product.categories[0].title else "none"
            containerView.tv_product_name.text = product.title
            containerView.tv_product_producer.text = product.producer
            containerView.tv_product_price.text = "${product.price} ₽"

           itemView.setOnClickListener {
                onItemClick?.invoke(product)
            }
            containerView.shoppingCart.setOnClickListener() {
                onCharClick?.invoke(it.id)
            }
        }
    }
}