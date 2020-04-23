package com.example.hw4l7.presenter

import com.example.hw4l7.model.OrderModel
import com.example.hw4l7.model.Product
import com.example.hw4l7.ui.ProductView
import moxy.MvpPresenter

class CheckoutPresenter : MvpPresenter<ProductView>() {
    private val products = mutableListOf(
        Product(
            price = 12300.5,
            salePercent = 30,
            productName = "Fender Telecaster"
        ),
        Product(
            price = 11900.0,
            salePercent = 5,
            productName = "Fender Stratocaster"
        ),
        Product(
            price = 11200.1,
            salePercent = 5,
            productName = "Gibson Les Paul"
        ),
        Product(
            price = 11900.9,
            salePercent = 5,
            productName = "Gibson ES-335"
        ),
        Product(
            price = 11900.9,
            productName = "Gibson SG Standard"
        ),
        Product(
            price = 11200.0,
            salePercent = 13,
            productName = "FENDER SQUIER MM STRATOCASTER"
        ),
        Product(
            price = 17010.0,
            salePercent = 21,
            productName = "FENDER SQUIER BULLET STRAT"
        ),
        Product(
            price = 16400.0,
            salePercent = 2,
            productName = "IBANEZ GRX20-BKN BLACK"
        ),
        Product(
            price = 18900.0,
            productName = "FENDER SQUIER BULLET STRAT HT AWT"
        ),
        Product(
            price = 19000.0,
            productName = "IBANEZ GRX70QA-TRB"
        ),
        Product(
            price = 16920.0,
            productName = "FENDER SQUIER BULLET TREM HSS AWT"
        ),
        Product(
            price = 20100.0,
            productName = "EPIPHONE SG-SPECIAL VE CHERRY"
        )
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