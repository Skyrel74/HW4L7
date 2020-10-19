package com.example.hw4l7.ui.detailed

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.hw4l7.App
import com.example.hw4l7.R
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.presenter.detailed.DetailedPresenter
import com.example.hw4l7.ui.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_detailed.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class DetailedActivity : BaseActivity(),
    DetailedView {

    @Inject
    lateinit var detailedPresenter: DetailedPresenter

    private lateinit var auth: FirebaseAuth

    private val presenter by moxyPresenter { detailedPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        setSupportActionBar(detailedToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        val product = intent?.getParcelableExtra<RemoteProduct>(PRODUCT_TAG) ?: return
        Glide.with(this).load(product.img).into(DetailedIv)
        tvDetailedTitle.text = product.name
        tvDetailedPrice.text = product.price.toString()
        delProduct.setOnClickListener {
            presenter.onProductDel(product)
            finish()
        }

        val user = auth.currentUser?.email
        if (user != "root@gmail.com") {
            if (user != product.owner) {
                delProduct.isVisible = false
                delProduct.isEnabled = true
            }
            if (product.isAgreed != 1) {
                addToCartBtn.isVisible = false
                addToCartBtn.isEnabled = true
            }
            addToCartBtn.setOnClickListener {
                presenter.onProductAdd(product)
                finish()
            }
        } else {
            addToCartBtn.isVisible = false
            addToCartBtn.isEnabled = true
            if (product.isAgreed == -1) {
                delProduct.isVisible = false
                delProduct.isEnabled = true
            }
            addToCartBtn.text = "Одобрить"
            addToCartBtn.setOnClickListener {
                presenter.onProductAgreed(product)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return true
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}