package com.example.firebase.Views

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.R
import com.example.firebase.Utils.EventObserver
import com.example.firebase.ViewModel.AuthViewModel
import com.example.firebase.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tl2.setEndIconTintList(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.Gray
                )
            )
        )

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        subscribeToObservers()

        binding.signinBtnLogin.setOnClickListener {
            viewModel.login(
                binding.signinEtEmail.text.toString().trim(),
                binding.signinPassword.text.toString().trim()
            )
        }

        binding.signinBtnApiLogin.setOnClickListener {
            Toast.makeText(this, "Need Api For this...!!", Toast.LENGTH_SHORT).show()
        }

        binding.btnGoToRegister.setOnClickListener {

            val i = Intent(this, Signup::class.java)
            startActivity(i)
        }
    }

    private fun subscribeToObservers() {
        viewModel.loginStatus.observe(this, EventObserver(
            onError = {
                binding.loginProgressBar.isVisible = false
                binding.signinBtnLogin.isEnabled = true
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            },
            onLoading = {
                binding.loginProgressBar.isVisible = true
                binding.signinBtnLogin.isEnabled = false
            }
        ) {
            binding.loginProgressBar.isVisible = false
            binding.signinBtnLogin.isEnabled = true
            Toast.makeText(this, "Sign in Successfully..", Toast.LENGTH_SHORT).show()
        })
    }
}