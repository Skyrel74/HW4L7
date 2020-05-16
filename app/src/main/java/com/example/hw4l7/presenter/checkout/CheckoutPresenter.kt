package com.example.hw4l7.presenter.checkout

import com.example.hw4l7.data.AddedProductDaoImpl
import com.example.hw4l7.domain.model.OrderModel
import com.example.hw4l7.ui.checkout.CheckoutView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CheckoutPresenter @Inject constructor(
    private val addedProductDao: AddedProductDaoImpl
) : MvpPresenter<CheckoutView>() {

    private val model = OrderModel()

    private fun checkSymbols(text: String): Boolean = text.length < 3

    private fun checkNumbers(phone: String): Boolean = when (phone.length) {
        11 -> phone[0] != '8'
        12 -> !(phone[0] == '+' && phone[1] == '7')
        else -> true
    }

    fun checkFirstName(name: String) {
        if (!checkSymbols(name))
            model.firstName = name
        viewState.showErrorForName(checkSymbols(name))
    }

    fun checkSurname(surname: String) {
        if (!checkSymbols(surname))
            model.surname = surname
        viewState.showErrorForSurname(checkSymbols(surname))
    }

    fun checkSecondName(secondName: String) {
        if (!checkSymbols(secondName))
            model.secondName = secondName
        viewState.showErrorForSecondName(checkSymbols(secondName))
    }

    fun checkPhone(phone: String) {
        if (!checkNumbers(phone))
            model.phone = phone
        viewState.showErrorForPhone(checkNumbers(phone))
    }

    fun clearCart() = addedProductDao.clearCart()
}