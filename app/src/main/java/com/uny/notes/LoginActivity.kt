package com.uny.notes

import SessionManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.uny.notes.databinding.ActivityLoginBinding
import com.uny.notes.local.LoginDatabase
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        sessionManager = SessionManager(this)
        lifecycleScope.launch {
            localLogin()
        }


        binding.btnLogin.setOnClickListener {
            lifecycleScope.launch {
                localLogin()
            }

        }

        binding.tvRegister.setOnClickListener {
            lifecycleScope.launch {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)

            }
        }


    }

    private suspend fun localLogin() {
        val db = LoginDatabase.getDatabase(this)
        val dao = db?.userDao()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val userIsLogged = dao?.login(email, password)
        if (sessionManager.getUsername() != null || userIsLogged != false) {
            sessionManager.saveUser(email)
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } else {
            Toast.makeText(this, "Username atau password salah!", Toast.LENGTH_LONG)
                .show()
        }
    }


//
//    private fun loginUserAccount() {
//        binding.progressbar.visibility = View.VISIBLE
//        val email = binding.etEmail.text.toString()
//        val password = binding.etPassword.text.toString()
//
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(
//                this,
//                "Please enter email!!",
//                Toast.LENGTH_LONG
//            )
//                .show()
//        }
//        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(
//                this,
//                "Please enter passwprd!!",
//                Toast.LENGTH_LONG
//            )
//                .show()
//        }
//        mAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { p0 ->
//                if (p0.isSuccessful) {
//                    Toast.makeText(
//                        this@LoginActivity,
//                        "Login successful!!",
//                        Toast.LENGTH_LONG
//                    ).show()
//                    binding.progressbar.visibility = View.GONE
//                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(
//                        this@LoginActivity, "Login Failed!!",
//                        Toast.LENGTH_LONG
//                    ).show()
//                    binding.progressbar.visibility = View.GONE
//                }
//            }
//    }
}