package com.uny.notes

import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.play.integrity.internal.f
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.uny.notes.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.tvLogin.setOnClickListener {
            onBackPressed()
        }
        binding.btnRegister.setOnClickListener {
            registerNewUser()
        }


    }

    private fun registerNewUser() {
        binding.progressbar.visibility = View.VISIBLE
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter Your email!", Toast.LENGTH_SHORT)
                .show()
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter Your Password!", Toast.LENGTH_SHORT)
                .show()
        }
        mAuth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful) {
                        Toast.makeText(
                            getApplicationContext(),
                            "Registration successful!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        binding.progressbar.visibility = View.GONE

                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration failed!! Please try again later",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        binding.progressbar.visibility = View.GONE
                    }

                }

            })
    }
}