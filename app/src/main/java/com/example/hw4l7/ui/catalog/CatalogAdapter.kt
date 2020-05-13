package com.example.hw4l7.ui.catalog

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw4l7.R
import com.example.hw4l7.domain.RemoteProduct
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.catalog_item.*

class CatalogAdapter(
    private val onProductClick: (RemoteProduct) -> Unit
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    private var categories: List<RemoteProduct> = listOf()
    lateinit var context: Context

    fun setData(categories: List<RemoteProduct>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.catalog_item, parent, false)
        )
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(product: RemoteProduct) {
            containerView.setOnClickListener { onProductClick(product) }
            catalogItemTitle.text = product.name
            Glide.with(context).load(product.imageUrl).into(catalogItemImg)
        }
    }

}

class CharacterItemDecoration(
    private val offset: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutParams  = view.layoutParams as GridLayoutManager.LayoutParams

        outRect.top = offset
        if (layoutParams.spanIndex % 2 == 0) {
            outRect.left = offset
            outRect.right = offset / 2
        } else {
            outRect.left = offset / 2
            outRect.right = offset
        }

        super.getItemOffsets(outRect, view, parent, state)
    }
}