package com.example.hw4l7.presenter.cart

import com.example.hw4l7.domain.model.Cart
import com.example.hw4l7.domain.model.CartFactory
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CartPresenter : MvpPresenter<CartView>() {

    private val factory = CartFactory()

    private val myDataSet = listOf(
        factory.createCartProduct(1, "someProd0", "123321", 1200.0, 0),
        factory.createCartProduct(2, "someProd1", "123321", 1200.0, 0),
        factory.createCartProduct(3, "someProd2", "123321", 1200.0, 0),
        factory.createCartProduct(4, "someProd3", "123321", 1200.0, 0),
        factory.createCartProduct(5, "someProd4", "123321", 1200.0, 0),
        factory.createCartProduct(6, "someProd5", "123321", 1200.0, 0)
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getProducts()
    }

    private fun getProducts() {
        viewState.showProducts(myDataSet)
    }

    fun onProductClick(product: Cart) {
        viewState.showProductDerailed(product)
    }
}