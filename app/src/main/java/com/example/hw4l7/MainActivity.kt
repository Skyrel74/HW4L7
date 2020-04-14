package com.example.hw4l7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round

class MainActivity : AppCompatActivity(), ProductView {

    private val presenter = CartPresenter()
    private val tableLayout by lazy { TableLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)

        presenter.printAllProducts()
        setListeners()
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

    override fun print(products: List<Product>) {
        val header = listOf(
            resources.getString(R.string.Price),
            resources.getString(R.string.Discount),
            resources.getString(R.string.Result)
        )

        val rows = products.size
        // Захардкодил, потому что не знал как взять число полей класса
        val columns = 3
        val tableParams = TableLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        tableLayout.apply {
            layoutParams = tableParams
            isStretchAllColumns = true
        }

        val headerRow = TableRow(this)
        headerRow.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        for (j in 0 until columns) {
            val textView = TextView(this).apply {
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                text = header[j]
            }
            headerRow.addView(textView)
        }
        tableLayout.addView(headerRow)

        for (i in 0 until rows) {
            val row = TableRow(this)
            row.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            val textViews = listOf(
                TextView(this),
                TextView(this),
                TextView(this)
            )
            textViews.forEach {
                it.apply {
                    layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )
                }
            }
            textViews[0].text = formatPrice(products[i].getPrice())
            textViews[1].text = products[i].getSalePercent().toString()
            textViews[2].text = formatPrice(products[i].calcDiscountPrice())
            textViews.forEach { row.addView(it) }

            tableLayout.addView(row)
        }

        linearLayout.addView(tableLayout)
    }

    override fun print(name: String, price: Double) {
        Log.d("App Output", "$name: ${formatPrice(price)}P")
    }
}