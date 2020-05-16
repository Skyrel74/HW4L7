package com.example.hw4l7.presenter.catalog

import com.example.hw4l7.domain.Category
import com.example.hw4l7.domain.MainApi
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.presenter.BasePresenter
import com.example.hw4l7.ui.catalog.CatalogView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import javax.inject.Inject

@InjectViewState
class CatalogPresenter @Inject constructor(
    private val mainApi: MainApi
) : BasePresenter<CatalogView>() {

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
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

    fun onProductClick(product: RemoteProduct) = viewState.showProductDetailed(product)

    fun onCategoryClick(category: Category) = viewState.setProducts(category.products)
}