package com.example.hw4l7.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4l7.R
import com.example.hw4l7.domain.RemoteProduct
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cart_item.*

class CartAdapter(
    private val onDeleteClick: (product: RemoteProduct) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var dataSet: MutableList<RemoteProduct> = mutableListOf()

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(product: RemoteProduct) {
            tvCartItemTitle.text = product.name
            tvCartItemPrice.text = product.price.toString()
            tvCartItemDiscount.text = "${product.discountPercent} %"
            tvCartItemDiscountPrice.text = product.calcDiscountPrice().toString()
            deleteProduct.setOnClickListener {
                onDeleteClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false) as ConstraintLayout
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    fun changeItemSource(products: MutableList<RemoteProduct>) {
        dataSet = products
        notifyDataSetChanged()
    }

    fun removeItem(product: RemoteProduct) {
        val position = dataSet.indexOf(product)
        if (position != -1)
            dataSet.removeAt(position)
        notifyItemRemoved(position)
    }
}