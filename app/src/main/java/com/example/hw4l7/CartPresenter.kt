package com.example.hw4l7

import moxy.MvpPresenter

class CartPresenter : MvpPresenter<ProductView>() {
    private val products = listOf(
        Product(price = 123.5, salePercent = 30, productName = "Fender Telecaster"),
        Product(price = 119.0, salePercent = 5, productName = "Fender Stratocaster"),
        Product(price = 112.1, salePercent = 5, productName = "Gibson Les Paul"),
        Product(price = 119.9, salePercent = 5, productName = "Gibson ES-335"),
        Product(price = 119.9, productName = "Gibson SG Standard")
    )

    private val model = OrderModel()

    private fun checkSymbols(text: String): Boolean = text.length < 3

    private fun checkNumbers(phone: String): Boolean = when (phone.length) {
        11 -> phone[0] != '8'
        12 -> !(phone[0] == '+' && phone[1] == '7')
        else -> true
    }

    fun printAllProducts() = viewState.print(products)

    fun printTotalPrice() = viewState.print(products.sumByDouble { it.calcDiscountPrice() })

    fun checkFirstName(name: String) {
        if (!checkSymbols(name)) model.firstName
        viewState.showErrorForName(checkSymbols(name))
    }

    fun checkSurname(surname: String) {
        if (!checkSymbols(surname)) model.surnname
        viewState.showErrorForSurname(checkSymbols(surname))
    }

    fun checkSecondName(secondName: String) {
        if (!checkSymbols(secondName)) model.secondName
        viewState.showErrorForSecondName(checkSymbols(secondName))
    }

    fun checkPhone(phone: String) {
        if (!checkNumbers(phone)) model.phone
        viewState.showErrorForPhone(checkNumbers(phone))
    }
}