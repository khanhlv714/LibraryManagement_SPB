package com.example.myapplication.feature.auth

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.core.base.BaseActivity
import com.example.myapplication.core.util.Constants
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.feature.admin.home.AdminHomeActivity
import com.example.myapplication.feature.initdata.InitDataActivity
import com.example.myapplication.feature.librarian.home.LibrarianHomeActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

//import com.example.myapplication.core.datastore.dataStore


//private val Context.dataStore by preferencesDataStore(name = "session")

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
        observeState()
    }

    private fun initListener() {

        binding.btnLogin.setOnClickListener {
            val userName = binding.edtUserName.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            if (userName.length == 0 || password.length == 0) {
                showToast("Username and password cannot be empty")
                return@setOnClickListener;
            }

            viewModel.login(
                binding.edtUserName.text.toString(),
                binding.edtPassword.text.toString(),
            )

        }

    }

    private fun observeState() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect { state ->

                    if (state.isLoading) {
                        // Show Loading
                    }

                    state.error?.let{
                        val snackbar =
                            Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT);
                        snackbar.setTextColor(Color.WHITE)
                        snackbar.show();
                    }

                    if (state.isSuccess != null && state.isSuccess) {
                        if (state.isDatabaseInitialized!! == false) {
                            navigateToInitData(state.role!!)
                        } else navigateToHome(state.role!!)
                    }
                }
            }

        }
    }

    fun navigateToHome(role: String) {
        if (role == Constants.ROLE_LIBRARIAN) startActivity(
            Intent(
                this@LoginActivity, LibrarianHomeActivity::class.java
            )

        ) else {
            startActivity(
                Intent(
                    this@LoginActivity, AdminHomeActivity::class.java
                )

            )
        }
        finish()
    }

    fun navigateToInitData(role: String) {
        val intent = Intent(
            this@LoginActivity, InitDataActivity::class.java
        )
        intent.putExtra("role", role)
        startActivity(intent)
    }


}