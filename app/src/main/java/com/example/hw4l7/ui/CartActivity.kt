package com.example.hw4l7.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw4l7.R
import com.example.hw4l7.model.Product
import com.example.hw4l7.presenter.CartPresenter
import kotlinx.android.synthetic.main.cart_layout.*

class CartActivity : BaseActivity(), CartView {

    private val presenter = CartPresenter()
    private val adapter = CartAdapter { product ->
        presenter.removeItem(product)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        cartRv.layoutManager = LinearLayoutManager(this)
        cartRv.adapter = adapter

        presenter.attachView(this)
        presenter.setData()

        cartAddBtn.setOnClickListener {
            presenter.addItem()
        }


        /*
        setSupportActionBar(cartToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        */
    }

    override fun setProducts(products: List<Product>) {
        adapter.setData(products)
    }

    override fun removeItem(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun addItem(position: Int) {
        adapter.notifyItemInserted(position)
    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return true
    }*/
}