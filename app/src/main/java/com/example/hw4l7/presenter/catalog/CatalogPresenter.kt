package com.example.hw4l7.presenter.catalog

import android.util.Log
import com.example.hw4l7.domain.Category
import com.example.hw4l7.domain.MainApi
import com.example.hw4l7.domain.RemoteProduct
import com.example.hw4l7.presenter.BasePresenter
import com.example.hw4l7.ui.catalog.CatalogView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import javax.inject.Inject

@InjectViewState
class CatalogPresenter @Inject constructor(
    private val mainApi: MainApi
) : BasePresenter<CatalogView>() {

    private lateinit var auth: FirebaseAuth
    private lateinit var refProds: DatabaseReference
    private lateinit var refUsers: DatabaseReference
    private var role: String? = null

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
        launch {
            auth = FirebaseAuth.getInstance()
            refUsers = FirebaseDatabase.getInstance().reference.child("users")
            val valueEventListener = object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        val email = ds.child("email").getValue(String::class.java)
                        val password = ds.child("password").getValue(String::class.java)
                        val r = ds.child("role").getValue(String::class.java)
                        val uid = ds.child("uid").getValue(String::class.java)
                        if (uid == auth.currentUser!!.uid)
                            role = r
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("onCancelledError", "onCancelled", error.toException())
                }

            }
            refUsers.addListenerForSingleValueEvent(valueEventListener)

            refProds = FirebaseDatabase.getInstance().reference
            refProds.child("products").addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val prods = mutableListOf<RemoteProduct>()
                    for (postSnapshot: DataSnapshot in snapshot.children) {
                        val prod = postSnapshot.getValue(RemoteProduct::class.java)
                        if (prod != null) {
                            prod.isAgreed =
                                postSnapshot.child("isAgreed").getValue(Int::class.java)!!
                            prods.add(prod)
                        }
                    }

                    var products: List<Category> = listOf()

                    if (auth.currentUser!!.email == "root@gmail.com") {
                        products = listOf(
                            Category("Одобренные продукты",
                                prods.filter { it.isAgreed == 1 }
                            ),
                            Category("Неодобренные продукты",
                                prods.filter { it.isAgreed == -1 }
                            )
                        )
                    } else {
                        products = listOf(
                            Category("Все продукты",
                                prods.filter { it.isAgreed == 1 }
                            ),
                            Category("Мои продукты",
                                prods.filter { it.owner == auth.currentUser!!.email }
                            )
                        )
                    }

                    viewState.setCategories(products)
                    viewState.setProducts(products[0].products)
                }

                override fun onCancelled(error: DatabaseError) {
                    println(error.message)
                }

            })
        }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        viewState.logout()
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is ConnectException)
            viewState.showInternetError()
    }

    fun onProductClick(product: RemoteProduct) = viewState.showProductDetailed(product)

    fun onCategoryClick(category: Category) = viewState.setProducts(category.products)
}