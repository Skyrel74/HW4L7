package com.example.hw4l7.presenter.signup

import android.util.Log
import com.example.hw4l7.presenter.BasePresenter
import com.example.hw4l7.ui.signup.SignUpView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import moxy.InjectViewState
import java.net.ConnectException
import javax.inject.Inject

@InjectViewState
class SignUpPresenter @Inject constructor() : BasePresenter<SignUpView>() {

    private lateinit var auth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = ""

    fun signUpUser() {
        auth = FirebaseAuth.getInstance()
        viewState.checkForm()
    }

    fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseUserId = auth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference
                        .child("users")
                        .child(firebaseUserId)
                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["email"] = email
                    userHashMap["role"] = "user"

                    refUsers.updateChildren(userHashMap)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful)
                                viewState.finishSignUp()
                        }
                } else
                    viewState.showSignUpError()
            }
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is ConnectException)
            Log.d("Exception in Sign Up: ", e.toString())
    }
}