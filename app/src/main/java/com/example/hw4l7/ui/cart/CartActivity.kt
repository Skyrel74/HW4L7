package com.example.hw4l7.ui.cart

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw4l7.R
import com.example.hw4l7.domain.model.Cart
import com.example.hw4l7.domain.model.Product
import com.example.hw4l7.presenter.cart.CartPresenter
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.presenter.cart.CartView
import com.example.hw4l7.ui.DetailedActivity
import com.example.hw4l7.ui.DetailedActivity.Companion.PRODUCT_TAG
import kotlinx.android.synthetic.main.activity_cart.*
import moxy.ktx.moxyPresenter

class CartActivity : BaseActivity(),
    CartView {

    private lateinit var cardAdapter: CartAdapter
    private val presenter by moxyPresenter { CartPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        with(cartProducts) {
            layoutManager = LinearLayoutManager(context)
            adapter = CartAdapter {
                presenter.onProductClick(it)
            }
                .also { cardAdapter = it }
        }
    }

    override fun showProducts(products: List<Cart>) {
        cardAdapter.changeItemSource(products)
    }

    override fun showProductDerailed(product: Cart) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(PRODUCT_TAG, product)
        })
    }
}