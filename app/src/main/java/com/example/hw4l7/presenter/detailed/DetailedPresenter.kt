package com.example.hw4l7.presenter.detailed

import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.domain.ViewedProductDao
import com.example.hw4l7.domain.model.Cart
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailedPresenter(private val viewedProductDao: ViewedProductDao) :
    MvpPresenter<DetailedView>() {
    fun onProductShow(product: RemoteProduct) {
        //viewedProductDao.addProduct(product)
    }
}