package com.example.hw4l7.ui.catalog

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hw4l7.R
import com.example.hw4l7.data.ViewedProductDaoImpl
import com.example.hw4l7.domain.MainApi
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.presenter.catalog.CatalogPresenter
import com.example.hw4l7.presenter.catalog.CatalogView
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.DetailedActivity
import com.example.hw4l7.ui.DetailedActivity.Companion.PRODUCT_TAG
import com.example.hw4l7.ui.cart.CartActivity
import com.example.hw4l7.ui.checkout.CheckoutActivity
import kotlinx.android.synthetic.main.activity_catalog.*
import moxy.ktx.moxyPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CatalogActivity : BaseActivity(), CatalogView {

    private lateinit var catalogAdapter: CatalogAdapter

    private val presenter by moxyPresenter {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://207.254.71.167:9191")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MainApi::class.java)
        CatalogPresenter(
            mainApi = service,
            viewedProductDao = ViewedProductDaoImpl(sharedPreferences)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        with(catalogRv) {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(CharacterItemDecoration(10))
            adapter = CatalogAdapter {
                presenter.onProductClick(it)
            }
                .also { catalogAdapter = it }
        }
    }

    override fun setProducts(list: List<RemoteProduct>) {
        catalogAdapter.setData(list)
    }

    override fun showInternetError() {
        Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show()
    }

    override fun showProductDetailed(product: RemoteProduct) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(PRODUCT_TAG, product)
        })
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH: Int = 10
        const val IS_USER_AUTH = "IS_USER_AUTH"
    }

    private val AppCompatActivity.sharedPreferences: SharedPreferences
        get() = getSharedPreferences("data", MODE_PRIVATE)
}