package com.example.hw4l7.ui.detailed

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hw4l7.App
import com.example.hw4l7.R
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.presenter.detailed.DetailedPresenter
import com.example.hw4l7.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_detailed.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class DetailedActivity : BaseActivity(),
    DetailedView {

    @Inject
    lateinit var detailedPresenter: DetailedPresenter

    private val presenter by moxyPresenter { detailedPresenter }

    private lateinit var paramAdapter: ParamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        setSupportActionBar(detailedToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val product = intent?.getParcelableExtra<RemoteProduct>(PRODUCT_TAG) ?: return
        Glide
            .with(DetailedIv.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(DetailedIv)
        tvDetailedTitle.text = product.name
        tvDetailedPrice.text =
            product.calcDiscountPrice().toString() + resources.getString(R.string.rouble)
        addToCartBtn.setOnClickListener {
            presenter.onProductAdd(product)
        }
        tvDetailedDescription.text = product.description
        with(paramsRv) {
            layoutManager = LinearLayoutManager(context)
            adapter = ParamAdapter()
                .also { paramAdapter = it }
        }
        presenter.setData(product.attributes)
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

    override fun setParams(list: List<RemoteProduct.Attribute>) {
        paramAdapter.setData(list)
    }
}