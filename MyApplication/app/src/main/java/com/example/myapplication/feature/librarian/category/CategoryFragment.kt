package com.example.myapplication.feature.librarian.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.R
import com.example.myapplication.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment() {

    private val viewModel: CategoryViewModel by viewModels()

    private lateinit var listView: ListView
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_category,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.lvCategories)

        // Adapter ban đầu chưa có dữ liệu
        adapter = CategoryAdapter(emptyList())
        listView.adapter = adapter

        // Quan sát dữ liệu từ ViewModel
         observeUiState()

        // Gọi API thông qua ViewModel
        viewModel.loadCategorys()
    }
    fun observeUiState(){
        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ){

                viewModel.uiState.collect { state ->

                    adapter.updateData(state.categories)

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