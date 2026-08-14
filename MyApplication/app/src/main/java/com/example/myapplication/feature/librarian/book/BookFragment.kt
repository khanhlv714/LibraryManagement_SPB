package com.example.myapplication.feature.librarian.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import com.example.myapplication.R
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.core.base.BaseFragment
import com.example.myapplication.databinding.FragmentBookBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class BookFragment : BaseFragment() {

    private val viewModel: BookViewModel by viewModels()
    private lateinit var listView: ListView;
    private lateinit var adapter: BookAdapter;
    private lateinit var binding : FragmentBookBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        listView = binding.lvBooks

        // Adapter ban đầu chưa có dữ liệu
        adapter = BookAdapter(emptyList())
        listView.adapter = adapter

        // Quan sát dữ liệu từ ViewModel

        observeUiState();
        // Gọi API thông qua ViewModel
        viewModel.loadBooks()
    }
    private fun observeUiState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ){

                viewModel.uiState.collect { state ->

                    adapter.updateData(state.books)

                    if (state.isLoading) {
                        // Hiện ProgressBar
                    } else {
                        // Ẩn ProgressBar
                    }

                    state.errorMessage?.let { message ->
                        showToast(message)
                    }
                }
            }
        }
    }

}