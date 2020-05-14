package com.example.hw4l7.presenter.catalog

import com.example.hw4l7.domain.Category
import com.example.hw4l7.domain.MainApi
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.presenter.BasePresenter
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException

@InjectViewState
class CatalogPresenter(
    private val mainApi: MainApi
) : BasePresenter<CatalogView>() {

    private val list = mutableListOf<RemoteProduct>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val products = mainApi.allProducts(author = "Rakipov")
            viewState.setCategories(products)
            viewState.setProducts(products[0].products)
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

    fun onCategoryClick(category: Category) {
        viewState.setProducts(category.products)
    }
}