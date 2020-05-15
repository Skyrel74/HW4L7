package com.example.hw4l7.presenter.cart

import com.example.hw4l7.data.AddedProductDaoImpl
import com.example.hw4l7.domain.RemoteProduct
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

    fun deleteProduct(product: RemoteProduct) {
        addedProductDao.remove(product)
        viewState.removeItem(product)
    }
}