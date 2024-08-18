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
import com.example.firebase.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Signup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.tl3.setEndIconTintList(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.Gray
                )
            )
        )

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        subscribeToObservers()

        binding.signupBtnRegister.setOnClickListener {
            viewModel.register(
                binding.signupEtEmail.text.toString().trim(),
                binding.signupEtName.text.toString().trim(),
                binding.signupEtPassword.text.toString().trim()
            )
        }

        binding.signupBtnApi.setOnClickListener {
            Toast.makeText(this, "Need Api for this...!!", Toast.LENGTH_SHORT).show()
        }

        binding.signupBtnGoToLogin.setOnClickListener {

            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }

    private fun subscribeToObservers() {
        viewModel.registerStatus.observe(this, EventObserver(
            onError = {
                binding.registerProgressBar.isVisible = false
                binding.signupBtnRegister.isEnabled = true
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            },
            onLoading = {
                binding.registerProgressBar.isVisible = true
                binding.signupBtnRegister.isEnabled = false
            }
        ) {
            binding.registerProgressBar.isVisible = false
            binding.signupBtnRegister.isEnabled = true
            Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
                this.finish()
            }
        })
    }
}