package com.example.hw4l7.presenter.addproduct

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.hw4l7.presenter.BasePresenter
import com.example.hw4l7.ui.addproduct.AddProductView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import moxy.InjectViewState
import java.io.ByteArrayOutputStream
import java.net.ConnectException
import java.util.*

@InjectViewState
class AddProductPresenter @javax.inject.Inject constructor() : BasePresenter<AddProductView>() {

    private lateinit var refProducts: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    fun addProduct(price: Double, name: String, img: Bitmap, owner: String) {

        val productId = UUID.randomUUID().toString()
        refProducts = FirebaseDatabase.getInstance().reference
            .child("products")
            .child(productId)
        val userHashMap = HashMap<String, Any>()
        userHashMap["uid"] = productId
        userHashMap["price"] = price
        userHashMap["name"] = name
        userHashMap["img"] = bitMapToString(img)
        userHashMap["owner"] = owner
        userHashMap["isAgreed"] = -1

        refProducts.updateChildren(userHashMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                    viewState.successfulAdded()
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bitMapToString(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val b = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    // TODO(Использовать парсер stringToBitMap)
    @RequiresApi(Build.VERSION_CODES.O)
    fun stringToBitMap(string: String): Bitmap {
        val imageBytes = Base64.decode(string, 0)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is ConnectException)
            Log.d("Exception in AddProd: ", e.toString())
    }
}