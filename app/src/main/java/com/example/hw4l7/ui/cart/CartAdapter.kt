package com.example.hw4l7.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4l7.R
import com.example.hw4l7.domain.model.Cart
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cart_item.*

class CartAdapter(
    private val onProductClick: (Cart) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var dataSet: List<Cart> = listOf()

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(item: Cart) {
            containerView.setOnClickListener { onProductClick(item) }
            tvCartItemTitle.text = item.product.name
            tvCartItemPrice.text = item.product.price.toString()
            tvCartItemDiscount.text = "${item.product.salePercent} %"
            tvCartItemDiscountPrice.text = item.product.calcDiscountPrice().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false) as ConstraintLayout
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataSet.size

    fun changeItemSource(products: List<Cart>) {
        dataSet = products
        notifyDataSetChanged()
    }
}