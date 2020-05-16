package com.example.hw4l7.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.hw4l7.domain.AddedProductDao
import com.example.hw4l7.domain.RemoteProduct
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class AddedProductDaoImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AddedProductDao {

    private var savedProducts: MutableList<RemoteProduct>
        get() {
            val historyStr = sharedPreferences.getString(PRODUCT_TAG, null)
            return if (historyStr != null) {
                Gson().fromJson(
                    historyStr,
                    (object : TypeToken<MutableList<RemoteProduct>>() {}).type
                )
            } else mutableListOf()
        }
        set(value) {
            sharedPreferences.edit {
                putString(PRODUCT_TAG, Gson().toJson(value)).apply()
            }
        }

    override fun addProduct(product: RemoteProduct) {
        val products: MutableList<RemoteProduct> = savedProducts
        val newProducts = mutableListOf<RemoteProduct>().apply {
            add(product)
            addAll(products.filter { it.id != product.id })
        }
        savedProducts = newProducts
    }

    override fun getAllProducts(): MutableList<RemoteProduct> = savedProducts

    override fun clearCart() {
        savedProducts = mutableListOf()
    }

    override fun remove(product: RemoteProduct) {
        val newCart = savedProducts
        newCart.remove(product)
        savedProducts = newCart
    }

    companion object {
        private const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}