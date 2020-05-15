package com.example.hw4l7.presenter.detailed

import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.domain.interactor.AddProductToCartUseCase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class DetailedPresenter @Inject constructor(
    private val addProductToCartUseCase: AddProductToCartUseCase
) : MvpPresenter<DetailedView>() {

    fun onProductAdd(product: RemoteProduct) {
        addProductToCartUseCase(product)
    }

    fun setData(productParam: List<RemoteProduct.Attribute>) = viewState.setParams(productParam)
}