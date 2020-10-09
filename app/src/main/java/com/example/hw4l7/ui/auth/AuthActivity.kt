package com.example.hw4l7.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hw4l7.R
import com.example.hw4l7.presenter.auth.AuthPresenter
import com.example.hw4l7.ui.BaseActivity
import com.example.hw4l7.ui.catalog.CatalogActivity
import com.example.hw4l7.ui.signup.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_signup.*
import moxy.ktx.moxyPresenter

class AuthActivity : BaseActivity(), AuthView {

    private lateinit var auth: FirebaseAuth

    private val presenter by moxyPresenter { AuthPresenter() }

    // TODO(Implement Log out)
//    public override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        updateUI(currentUser)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        auth = FirebaseAuth.getInstance()

        LogInButton.setOnClickListener {
            presenter.doLogin()
        }

        SignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    override fun checkForm() {
        val email = SignUpEmail.text.toString()
        val password = SignUpPassword.text.toString()

        if (email.isEmpty()) {
            EmailAuth.error = "Please enter email"
            EmailAuth.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(EmailAuth.text.toString()).matches()) {
            EmailAuth.error = "Please enter valid email"
            EmailAuth.requestFocus()
            return
        }

        if (PasswordAuth.text.toString().isEmpty()) {
            PasswordAuth.error = "Please enter password"
            PasswordAuth.requestFocus()
            return
        }

        if (password.length < 6) {
            SignUpPassword.error = "Password must be at least 6 characters"
            SignUpPassword.requestFocus()
            return
        }
        presenter.logIn(SignUpEmail.text.toString(), SignUpPassword.text.toString())
    }

    override fun showLogInError() =
        Toast.makeText(
            this, "Log In failed. Try again after some time.",
            Toast.LENGTH_SHORT
        ).show()

    override fun moveToCatalog() {
        startActivity(
            Intent(this, CatalogActivity::class.java)
                .addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                )
        )
        finish()
    }
}