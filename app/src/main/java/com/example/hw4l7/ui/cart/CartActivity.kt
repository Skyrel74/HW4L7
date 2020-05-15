package com.example.hw4l7.ui.cart

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw4l7.App
import com.example.hw4l7.R
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.presenter.cart.CartPresenter
import com.example.hw4l7.presenter.cart.CartView
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.catalog.CatalogActivity
import com.example.hw4l7.ui.checkout.CheckoutActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_cart.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CartActivity : BaseActivity(), CartView,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var cardAdapter: CartAdapter

    @Inject
    lateinit var cartPresenter: CartPresenter

    private val presenter by moxyPresenter { cartPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        with(cartProducts) {
            layoutManager = LinearLayoutManager(context)
            adapter = CartAdapter { product ->
                presenter.deleteProduct(product)
            }
                .also { cardAdapter = it }
        }
        btnApply.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.catalogBN -> {
                startActivity(Intent(this, CatalogActivity::class.java))
                return true
            }
            R.id.cartBN -> {
                return true
            }
        }
        return false
    }

    override fun showProducts(products: MutableList<RemoteProduct>) {
        cardAdapter.changeItemSource(products)
    }

    override fun removeItem(product: RemoteProduct) {
        cardAdapter.removeItem(product)
    }

    private val AppCompatActivity.sharedPreferences: SharedPreferences
        get() = getSharedPreferences("data", MODE_PRIVATE)
}