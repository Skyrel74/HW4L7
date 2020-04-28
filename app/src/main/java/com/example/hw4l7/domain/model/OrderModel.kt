package com.example.hw4l7.domain.model

/**
 * Модель для создания заказа
 */
data class OrderModel(
    var firstName: String = "",
    var surname: String = "",
    var secondName: String = "",
    var phone: String = ""
)