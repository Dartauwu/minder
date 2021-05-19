package com.darta.minder

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.applyLinks
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btn_Login.setOnClickListener {
            val email = et_email_Login.text.toString().trim()
            val password = et_password_Login.text.toString().trim()

            if (email.isEmpty()){
                et_email_Login.error = "Email harus diIsi"
                et_email_Login.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email_Login.error = "Email harus diIsi"
                et_email_Login.requestFocus()
                return@setOnClickListener

            }
            if (password.isEmpty() || password.length < 8 ) {
                et_password_Login.error = "Password harus lebih dari 8 karakter"
                et_password_Login.requestFocus()
                return@setOnClickListener
            }

            loginUser(email,password)

        }

        link_Reset.setOnClickListener {
            Intent(this@LoginActivity, ResetActivity::class.java ).also {
                startActivity(it)
            }
        }
        link_Register.setOnClickListener {
           Intent(this@LoginActivity, RegisterActivity::class.java).also {
               startActivity(it)
           }
        }


        textResetdandaftar()

    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful){
                        Intent(this@LoginActivity,MainActivity::class.java).also {intent ->
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    }else{
                        Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                    }
                }

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@LoginActivity,MainActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }
    }

    private fun textResetdandaftar() {

        val linkReset: Link = Link ("Reset")
            .setTextColor(Color.BLACK)
            .setTextColorOfHighlightedLink(Color.WHITE)
            .setHighlightAlpha(5f)
            .setBold(true)
        link_Reset.applyLinks(linkReset)

        val linkRegister: Link = Link ("Register")
            .setTextColor(Color.BLACK)
            .setTextColorOfHighlightedLink(Color.WHITE)
            .setHighlightAlpha(5f)
            .setBold(true)
        link_Register.applyLinks(linkRegister)

    }
}