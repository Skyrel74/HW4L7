package com.example.hw4l7.ui.detailed

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hw4l7.R
import com.example.hw4l7.data.AddedProductDaoImpl
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.presenter.detailed.DetailedPresenter
import com.example.hw4l7.presenter.detailed.DetailedView
import com.example.hw4l7.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_detailed.*
import moxy.ktx.moxyPresenter

class DetailedActivity : BaseActivity(), DetailedView {

    private lateinit var paramAdapter: ParamAdapter

    private val presenter by moxyPresenter {
        DetailedPresenter(
            AddedProductDaoImpl(sharedPreferences)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val product = intent?.getParcelableExtra<RemoteProduct>(PRODUCT_TAG) ?: return
        Glide
            .with(ivDetailedImage.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(ivDetailedImage)
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

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }

    private val AppCompatActivity.sharedPreferences: SharedPreferences
        get() = getSharedPreferences("data", MODE_PRIVATE)

    override fun setParams(list: List<RemoteProduct.Attribute>) {
        paramAdapter.setData(list)
    }
}