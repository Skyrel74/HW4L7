package com.example.hw4l7.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.TableLayout
import com.example.hw4l7.R
import com.example.hw4l7.domain.model.Product
import com.example.hw4l7.presenter.checkout.CheckoutPresenter
import com.example.hw4l7.presenter.checkout.CheckoutView
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.catalog.CatalogActivity.Companion.IS_USER_AUTH
import com.example.hw4l7.ui.catalog.CatalogActivity.Companion.PRODUCT_ID
import com.example.hw4l7.ui.catalog.CatalogActivity.Companion.REQUEST_AUTH
import kotlinx.android.synthetic.main.activity_checkout.*
import moxy.ktx.moxyPresenter
import kotlin.math.round


class CheckoutActivity : BaseActivity(), CheckoutView {

    private val presenter by moxyPresenter {
        CheckoutPresenter()
    }
    private var isAuth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        setSupportActionBar(checkoutToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val productId: Int? = intent.extras?.getInt(PRODUCT_ID, -1)
        Log.d(tag, productId.toString())

        setListeners()

        PayButton.setOnClickListener {
            isAuth = true
            setResult(REQUEST_AUTH, Intent().apply {
                putExtra(IS_USER_AUTH, isAuth)
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return true
    }

    private fun setListeners() {
        FirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkFirstName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        Surname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkSurname(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        SecondName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkSecondName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        Phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPhone(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun EditText.showError(isVisible: Boolean) {
        val drawable = if (isVisible) R.drawable.ic_error else 0

        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }

    override fun showErrorForName(isVisible: Boolean) = FirstName.showError(isVisible)

    override fun showErrorForSurname(isVisible: Boolean) = Surname.showError(isVisible)

    override fun showErrorForSecondName(isVisible: Boolean) = SecondName.showError(isVisible)

    override fun showErrorForPhone(isVisible: Boolean) = Phone.showError(isVisible)

    private fun formatPrice(price: Double): String =
        if (price - price.toInt() == 0.0) "${price.toInt()}"
        else "${round(price * 100) / 100}"

    override fun print(price: Double) {
        Log.d("App Output", "${formatPrice(price)}P")
    }

    override fun print(products: List<Product>) {}

    override fun print(name: String, price: Double) {
        Log.d("App Output", "$name: ${formatPrice(price)}P")
    }
}
