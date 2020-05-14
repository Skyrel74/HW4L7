package com.example.hw4l7.presenter.detailed

import com.example.hw4l7.domain.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailedView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setParams(list: List<RemoteProduct.Attribute>)
}