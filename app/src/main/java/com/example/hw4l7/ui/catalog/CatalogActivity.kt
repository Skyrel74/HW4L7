package com.example.hw4l7.ui.catalog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw4l7.R
import com.example.hw4l7.data.ViewedProductDaoImpl
import com.example.hw4l7.presenter.catalog.CatalogPresenter
import com.example.hw4l7.presenter.catalog.CatalogView
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.checkout.CheckoutActivity
import com.example.hw4l7.ui.DetailedActivity
import com.example.hw4l7.ui.cart.CartActivity
import kotlinx.android.synthetic.main.activity_catalog.*
import moxy.ktx.moxyPresenter
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hw4l7.domain.model.Cart


class CatalogActivity : BaseActivity(),
    CatalogView {

    private val presenter by moxyPresenter {
        CatalogPresenter(ViewedProductDaoImpl(sharedPreferences))
    }

    private val adapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        categotyRv.layoutManager = LinearLayoutManager(this)
        categotyRv.adapter = adapter

        productInfoBtn.setOnClickListener {
            startActivity(Intent(this, DetailedActivity::class.java))
        }

        cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        Log.d(tag, "savedInstanceState = $savedInstanceState")
        val savedInt = savedInstanceState?.getInt(SAVE_SATE_INT)
        Log.d(tag, "savedInt $savedInt")

        catalogCheckoutbtn.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java).apply {
                putExtra(PRODUCT_ID, 1000)
            }
            startActivityForResult(intent,
                REQUEST_AUTH
            )
        }
    }

    override fun showProductIds(productIds: List<Long>) {
        adapter.setData(productIds)
        Toast.makeText(this, productIds.joinToString(","), Toast.LENGTH_LONG).show()
    }

    override fun showProducts(products: List<Cart>) {
        Toast.makeText(this, products.joinToString { "," }, Toast.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SAVE_SATE_INT, 89)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (REQUEST_AUTH == requestCode) {
            val isUserAuth = data?.extras?.getBoolean(IS_USER_AUTH)
            Log.d(tag, "onActivityResult ${isUserAuth.toString()}")
        }
    }

    override fun setCategories(list: List<Long>) {
        adapter.setData(list)
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH: Int = 10
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val SAVE_SATE_INT = "SAVE_SATE_INT"
    }

    private val AppCompatActivity.sharedPreferences: SharedPreferences
        get() = getSharedPreferences("data", MODE_PRIVATE)
}