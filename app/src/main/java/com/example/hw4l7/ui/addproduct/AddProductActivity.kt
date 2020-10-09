package com.example.hw4l7.ui.addproduct

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import com.example.hw4l7.R
import com.example.hw4l7.presenter.addproduct.AddProductPresenter
import com.example.hw4l7.ui.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_addproduct.*
import moxy.ktx.moxyPresenter

class AddProductActivity : BaseActivity(), AddProductView {

    private val presenter by moxyPresenter { AddProductPresenter() }

    private val GALLERY_REQUEST_CODE = 12334

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)

        imageView.setOnClickListener {
            val intent = Intent()
                .setType("image/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(
                Intent.createChooser(intent, "Выберите изображение"),
                GALLERY_REQUEST_CODE
            )
        }

        SendProductBtn.setOnClickListener {
            if (AddNameEditText.text.toString() != "" && AddPriceEditText.text.toString() != "") {
                addProduct(
                    AddNameEditText.text.toString(),
                    AddPriceEditText.text.toString().toDouble(),
                    imageView.drawable.toBitmap()
                )
                finish()
            } else
                showFillError()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val imageData = data.data
            imageView.setImageURI(imageData)
        }
    }

    override fun showFillError() =
        Toast.makeText(this, "Нужно заполнить имя и цену", Toast.LENGTH_LONG).show()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun addProduct(name: String, price: Double, img: Bitmap) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val owner = user.email
            if (owner != null)
                presenter.addProduct(price, name, img, owner)
        } else
            Toast.makeText(this, "Ошибка с логином", Toast.LENGTH_LONG).show()
    }

    override fun successfulAdded() =
        Toast.makeText(this, "Продукт добавлен успешно", Toast.LENGTH_SHORT).show()
}