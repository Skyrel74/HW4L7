package com.example.hw4l7.ui

interface PriceFormatter {
    /**
     * Function takes price and @return formatted price
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun formatPrice(price: Double): String
}