package com.uny.notes

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.uny.notes.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()



        binding.btnLogin.setOnClickListener {
            loginUserAccount()

        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUserAccount() {
        binding.progressbar.visibility = View.VISIBLE
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(
                this,
                "Please enter email!!",
                Toast.LENGTH_LONG
            )
                .show()
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(
                this,
                "Please enter passwprd!!",
                Toast.LENGTH_LONG
            )
                .show()
        }
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login successful!!",
                            Toast.LENGTH_LONG
                        ).show()
                        binding.progressbar.visibility = View.GONE
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@LoginActivity, "Login Failed!!",
                            Toast.LENGTH_LONG
                        ).show()
                        binding.progressbar.visibility = View.GONE
                    }
                }

            })
    }
}