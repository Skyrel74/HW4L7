package com.example.hw4l7.presenter.cart

import com.example.hw4l7.data.AddedProductDaoImpl
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CartPresenter(
    private val addedProductDao: AddedProductDaoImpl
) : MvpPresenter<CartView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getProducts()
    }

    private fun getProducts() = viewState.showProducts(addedProductDao.getAllProducts())
}