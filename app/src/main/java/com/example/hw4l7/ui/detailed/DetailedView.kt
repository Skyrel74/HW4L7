package com.example.hw4l7.ui.detailed

import com.example.hw4l7.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailedView : MvpView {

    /**
     * Set params as "$name: $value" format
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setParams(list: List<RemoteProduct.Attribute>)
}