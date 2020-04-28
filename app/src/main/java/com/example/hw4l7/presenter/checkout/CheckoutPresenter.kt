package com.example.hw4l7.presenter.checkout

import com.example.hw4l7.domain.model.OrderModel
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CheckoutPresenter : MvpPresenter<CheckoutView>() {

    private val model = OrderModel()

    private fun checkSymbols(text: String): Boolean = text.length < 3

    private fun checkNumbers(phone: String): Boolean = when (phone.length) {
        11 -> phone[0] != '8'
        12 -> !(phone[0] == '+' && phone[1] == '7')
        else -> true
    }

    fun checkFirstName(name: String) {
        if (!checkSymbols(name)) model.firstName
        viewState.showErrorForName(checkSymbols(name))
    }

    fun checkSurname(surname: String) {
        if (!checkSymbols(surname)) model.surname
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