package com.example.hw4l7.presenter

import com.example.hw4l7.ui.CatalogView
import moxy.MvpPresenter

class CatalogPresenter : MvpPresenter<CatalogView>() {

    private val list = mutableListOf("Телевизоры", "Телефоны", "Планшеты", "Часы")

    fun setData() = viewState.setCategories(list)

    fun removeItem(category: String) {
        val position = list.indexOf(category)
        list.remove(category)
        viewState.removeItem(position)
    }
}