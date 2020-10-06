package com.nickolay.redsofttesttask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nickolay.redsofttesttask.R
import com.nickolay.redsofttesttask.data.entity.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsRVAdapter (val onItemClick: ((Product) -> Unit)? = null) : RecyclerView.Adapter<ProductsRVAdapter.ViewHolder>() {

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
            containerView.tv_title.text = product.id.toString()
            containerView.tv_text.text = product.title

            itemView.setOnClickListener {
                onItemClick?.invoke(product)
            }
        }
    }
}