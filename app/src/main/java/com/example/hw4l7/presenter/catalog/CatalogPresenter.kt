package com.example.hw4l7.presenter.catalog

import com.example.hw4l7.domain.ViewedProductDao
import com.example.hw4l7.domain.model.Cart
import com.example.hw4l7.domain.model.CartFactory
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CatalogPresenter(
    private val viewedProductDao: ViewedProductDao
) : MvpPresenter<CatalogView>() {

    private val list = mutableListOf<Long>()

    private fun setData() = viewState.setCategories(list)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
        val products = viewedProductDao.getAllProductIds()
        viewState.showProductIds(products)
    }
}