package com.example.hw4l7.presenter.catalog

import com.example.hw4l7.domain.MainApi
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.domain.ViewedProductDao
import com.example.hw4l7.presenter.BasePresenter
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import java.net.UnknownHostException

@InjectViewState
class CatalogPresenter(
    private val viewedProductDao: ViewedProductDao,
    private val mainApi: MainApi
) : BasePresenter<CatalogView>() {

    private val list = mutableListOf<RemoteProduct>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val products = mainApi.allProducts(author = "default")
            viewState.setProducts(products)
        }
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is ConnectException)
            viewState.showInternetError()
    }

    fun onProductClick(product: RemoteProduct) {
        viewState.showProductDetailed(product)
    }
}