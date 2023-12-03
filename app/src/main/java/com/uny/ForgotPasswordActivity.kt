package com.uny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.uny.notes.databinding.ActivityForgotPasswordBinding
import com.uny.notes.local.LoginDatabase
import kotlinx.coroutines.launch

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            lifecycleScope.launch {
                changePassword()
            }
        }
    }


    private suspend fun changePassword(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        val db = LoginDatabase.getDatabase(this)
        val dao = db?.userDao()
        val emailCheck = dao?.getUserEmail(email)

        if (emailCheck != true){
            Toast.makeText(this,"Email tidak terdaftar",Toast.LENGTH_SHORT).show()
        }else{
            dao.updatePassword(email,password)
            Toast.makeText(this,"Password berhasil diubah",Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

    }
}