package com.example.hw4l7.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.hw4l7.App
import com.example.hw4l7.R
import com.example.hw4l7.presenter.checkout.CheckoutPresenter
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.catalog.CatalogActivity
import kotlinx.android.synthetic.main.activity_checkout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class CheckoutActivity : BaseActivity(),
    CheckoutView {

    @Inject
    lateinit var checkoutPresenter: CheckoutPresenter

    private val presenter by moxyPresenter { checkoutPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        setSupportActionBar(checkoutToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setListeners()

        PayButton.setOnClickListener {
            presenter.clearCart()
            Toast.makeText(this, "Покупка успешна", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, CatalogActivity::class.java))
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

    private fun setListeners() {
        FirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = presenter.checkFirstName(s.toString())

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        Surname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = presenter.checkSurname(s.toString())

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        SecondName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = presenter.checkSecondName(s.toString())

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        Phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = presenter.checkPhone(s.toString())

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
}
