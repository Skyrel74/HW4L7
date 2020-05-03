package com.example.hw4l7.presenter.catalog

import com.example.hw4l7.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(list: List<RemoteProduct>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showInternetError()
}