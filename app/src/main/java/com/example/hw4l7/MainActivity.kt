package com.example.hw4l7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round

class MainActivity : AppCompatActivity(), ProductView {
    private val presenter = CartPresenter(this)
    private val tableLayout by lazy { TableLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.printAllProducts()
    }

    override fun formatPrice(price: Double): String =
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
        val tableParams = TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        tableLayout.apply {
            layoutParams = tableParams
            isStretchAllColumns = true
        }

        val headerRow = TableRow(this)
        headerRow.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        for (j in 0 until columns) {
            val textView = TextView(this).apply {
                layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT)
                text = header[j]
            }
            headerRow.addView(textView)
        }
        tableLayout.addView(headerRow)

        for (i in 0 until rows) {
            val row = TableRow(this)
            row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

            val textViews = listOf(
                TextView(this),
                TextView(this),
                TextView(this))
            textViews.forEach { it.apply {
                layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT)
            } }
            textViews[0].text = formatPrice(products[i].getPrice())
            textViews[1].text = "${products[i].getSalePercent()}%"
            textViews[2].text = formatPrice(products[i].calcDiscountPrice())
            textViews.forEach { row.addView(it) }

            tableLayout.addView(row)
        }

        linearLayout.addView(tableLayout)
    }

    override fun print(name: String, price: Double) {
        Log.d("App Output","$name: ${formatPrice(price)}P")
    }
}
