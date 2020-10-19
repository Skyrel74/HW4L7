package com.example.hw4l7.presenter.detailed

import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.domain.interactor.AddProductToCartUseCase
import com.example.hw4l7.ui.detailed.DetailedView
import com.google.firebase.database.DatabaseReference
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class DetailedPresenter @Inject constructor(
    private val addProductToCartUseCase: AddProductToCartUseCase
) : MvpPresenter<DetailedView>() {

    private lateinit var database: DatabaseReference

    fun onProductAdd(product: RemoteProduct) = addProductToCartUseCase(product)

    fun onProductDel(product: RemoteProduct) {
        product.isAgreed = 0
    }

    fun onProductAgreed(product: RemoteProduct) {
        product.isAgreed = 1
    }
}