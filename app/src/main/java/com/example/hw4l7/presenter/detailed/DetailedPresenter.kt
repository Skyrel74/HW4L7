package com.example.hw4l7.presenter.detailed

import com.example.hw4l7.data.AddedProductDaoImpl
import com.example.hw4l7.domain.RemoteProduct
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailedPresenter(
    private val addedProductDao: AddedProductDaoImpl
) : MvpPresenter<DetailedView>() {
    fun onProductAdd(product: RemoteProduct) {
        addedProductDao.addProduct(product)
    }

    fun setData(productParam: List<RemoteProduct.Attribute>) = viewState.setParams(productParam)
}