package com.example.hw4l7.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4l7.R
import com.example.hw4l7.model.Product
import kotlinx.android.synthetic.main.item_cart.view.*
import kotlinx.android.synthetic.main.item_categoty.view.*

class CartAdapter(
    private val onDeleteClick: (product: Product) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var cart: List<Product> = mutableListOf()

    fun setData(cart: List<Product>) {
        this.cart = cart
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            itemView.productNameTv.text = product.getProductName()
            itemView.productPriceTv.text = "${product.getPrice()}P"
            itemView.productSaleTv.text = "${product.getSalePercent()}%"
            itemView.deleteProductIv.setOnClickListener {
                onDeleteClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        )

    override fun getItemCount(): Int = cart.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cart[position])
    }
}