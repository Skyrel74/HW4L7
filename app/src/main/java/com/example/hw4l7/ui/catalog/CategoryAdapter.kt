package com.example.hw4l7.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4l7.R
import com.example.hw4l7.domain.model.Cart
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.categoty_item.*

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categories: List<Cart> = listOf()

    fun setData(categories: List<Cart>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.categoty_item, parent, false)
        )

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(cart: Cart) {
            categotyIdTv.text = cart.id.toString()
            categotyNameTv.text = cart.product.name
            categotyPriceTv.text = cart.product.price.toString()
        }
    }

}