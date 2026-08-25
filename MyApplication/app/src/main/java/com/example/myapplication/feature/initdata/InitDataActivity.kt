package com.example.myapplication.feature.initdata

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.R
import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.util.Constants
import com.example.myapplication.databinding.ActivityInitDataBinding
import com.example.myapplication.feature.admin.home.AdminHomeActivity
import com.example.myapplication.feature.librarian.home.LibrarianHomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InitDataActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInitDataBinding

    private lateinit var role : String

    private val viewModel: InitDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        role = intent.extras!!.getString("role")!!

        binding = ActivityInitDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeState()

        setListener()

        syns()
    }
    fun setListener(){
        binding.btnRetry.setOnClickListener {
            syns()
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { result ->

                    when (result) {
                        is Resource.Success -> {
                            if(role == Constants.ROLE_LIBRARIAN)
                            startActivity(
                                Intent(
                                    this@InitDataActivity, LibrarianHomeActivity::class.java
                                )
                            )
                            else{
                                startActivity(
                                    Intent(
                                        this@InitDataActivity, AdminHomeActivity ::class.java
                                    )
                                )
                            }
                            finish()
                        }

                        is Resource.Error -> {
                            binding.progressBar.isVisible = false
                            binding.btnRetry.isVisible = true
                            binding.tvMessage.text = "Không thể đồng bộ dữ liệu"
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
    fun syns(){
        binding.progressBar.isVisible = true
        binding.btnRetry.isVisible = false
        binding.tvMessage.text = "Đang đồng bộ dữ liệu"
        viewModel.sync()
    }
}