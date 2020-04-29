package com.example.hw4l7.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.hw4l7.domain.ViewedProductDao
import com.example.hw4l7.domain.model.Cart
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ViewedProductDaoImpl(
    private val sharedPreferences: SharedPreferences
) : ViewedProductDao {

    private var savedProducts: List<Cart>
        get() {
            val historyStr = sharedPreferences.getString(PRODUCT_TAG, null)
            return if (historyStr != null) {
                Gson().fromJson(historyStr, (object : TypeToken<List<Cart>>() {}).type)
            } else emptyList()
        }
        set(value) {
            sharedPreferences.edit {
                putString(PRODUCT_TAG, Gson().toJson(value)).apply()
            }
        }

    override fun addProduct(product: Cart) {
        val products: List<Cart> = savedProducts
        val newProducts = mutableListOf<Cart>().apply {
            add(product)
            addAll(products.filter { it.id != product.id })
        }
        savedProducts = newProducts
    }

    override fun getAllProducts(): List<Cart> {
        return savedProducts
    }

    companion object {
        private const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}