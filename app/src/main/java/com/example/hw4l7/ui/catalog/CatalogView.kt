package com.example.hw4l7.ui.catalog

import com.example.hw4l7.domain.Category
import com.example.hw4l7.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatalogView : MvpView {

    /**
     * Set category products
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(list: List<RemoteProduct>)

    /**
     * Set category
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategories(list: List<Category>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showInternetError()

    /**
     * Show detailed information about product
     */
    @StateStrategyType(SkipStrategy::class)
    fun showProductDetailed(product: RemoteProduct)
}