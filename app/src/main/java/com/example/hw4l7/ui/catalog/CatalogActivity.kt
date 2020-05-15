package com.example.hw4l7.ui.catalog

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw4l7.R
import com.example.hw4l7.domain.Category
import com.example.hw4l7.domain.MainApi
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.presenter.catalog.CatalogPresenter
import com.example.hw4l7.presenter.catalog.CatalogView
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.cart.CartActivity
import com.example.hw4l7.ui.detailed.DetailedActivity
import com.example.hw4l7.ui.detailed.DetailedActivity.Companion.PRODUCT_TAG
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_catalog.*
import moxy.ktx.moxyPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatalogActivity : BaseActivity(), CatalogView,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var catalogAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    private val presenter by moxyPresenter {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://207.254.71.167:9191")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MainApi::class.java)
        CatalogPresenter(
            mainApi = service
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        with(categoryRV) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoryAdapter {
                presenter.onCategoryClick(it)
            }
                .also { categoryAdapter = it }
        }

        with(productRv) {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(CharacterItemDecoration(10))
            adapter = ProductAdapter {
                presenter.onProductClick(it)
            }
                .also { catalogAdapter = it }
        }

        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.catalogBN -> {
                return true
            }
            R.id.cartBN -> {
                startActivity(Intent(this, CartActivity::class.java))
                return true
            }
        }

        return false
    }

    override fun setProducts(list: List<RemoteProduct>) {
        catalogAdapter.setData(list)
    }

    override fun setCategories(list: List<Category>) {
        categoryAdapter.setData(list)
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
}