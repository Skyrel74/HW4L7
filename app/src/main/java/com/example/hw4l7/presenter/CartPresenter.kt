package com.example.hw4l7.presenter

import com.example.hw4l7.model.Product
import com.example.hw4l7.ui.CartView
import moxy.MvpPresenter

class CartPresenter : MvpPresenter<CartView>() {
    private val products = mutableListOf(
        Product(
            price = 12300.5,
            salePercent = 30,
            productName = "Fender Telecaster"
        ),
        Product(
            price = 11900.0,
            salePercent = 5,
            productName = "Fender Stratocaster"
        ),
        Product(
            price = 11200.1,
            salePercent = 5,
            productName = "Gibson Les Paul"
        ),
        Product(
            price = 11900.9,
            salePercent = 5,
            productName = "Gibson ES-335"
        ),
        Product(
            price = 11900.9,
            productName = "Gibson SG Standard"
        ),
        Product(
            price = 11200.0,
            salePercent = 13,
            productName = "FENDER SQUIER"
        ),
        Product(
            price = 17010.0,
            salePercent = 21,
            productName = "FENDER BULLET"
        ),
        Product(
            price = 16400.0,
            salePercent = 2,
            productName = "IBANEZ GRX20-BKN"
        ),
        Product(
            price = 18900.0,
            productName = "FENDER HT AWT"
        ),
        Product(
            price = 19000.0,
            productName = "IBANEZ GRX70QA"
        ),
        Product(
            price = 16920.0,
            productName = "FENDER HSS AWT"
        ),
        Product(
            price = 20100.0,
            productName = "EPIPHONE SG-SPECIAL"
        )
    )

    fun setData() {
        viewState.setProducts(products)
    }

    fun addItem() {
        products.add(
            Product(54.80, 5, "Медиатор")
        )
        viewState.addItem(products.size)
    }

    fun removeItem(product: Product) {
        val position = products.indexOf(product)
        products.remove(product)
        viewState.removeItem(position)
    }
}