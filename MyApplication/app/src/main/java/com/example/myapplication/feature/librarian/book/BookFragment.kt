package com.example.myapplication.feature.librarian.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.log
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.base.BaseFragment
import com.example.myapplication.databinding.FragmentBookBinding
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookFragment : BaseFragment() {

    private val viewModel: BookViewModel by viewModels()

    private lateinit var binding: FragmentBookBinding
    private lateinit var adapter: BookPagingAdapter
    private lateinit var categoryAdapter: ArrayAdapter<Category>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupCategory()
        eventListener()
        observeUiState()
    }

    private fun setupRecyclerView() {
        adapter = BookPagingAdapter()

        binding.rvBooks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@BookFragment.adapter
        }
    }

    private fun setupCategory() {

        categoryAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, mutableListOf<Category>()
        )

        categoryAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerCategory.adapter = categoryAdapter
    }

    private fun eventListener() {

        // Search
        binding.edtSearch.doAfterTextChanged { text ->
            viewModel.search(text?.toString().orEmpty())
        }

        // Category
        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val category = parent?.getItemAtPosition(position) as? Category ?: return

                    viewModel.setCategory(
                        if(category.id == -1 ) null
                        else category.id
                    )
                }

                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {
                    viewModel.setCategory(null)
                }
            }

        // Pull to refresh
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun observeUiState() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                launch {
                    viewModel.bookUiState.collect { state ->
                        binding.swipeRefresh.isRefreshing = state.isRefreshing

                        state.errorMessage?.let {
                            showToast(it)
                        }
                    }
                }

                launch {
                    viewModel.bookFilter.collect { pagingData ->
                        adapter.submitData(pagingData)
                    }
                }

                launch {
                    viewModel.categories.collect { categories ->
                        categories.size;
                        categoryAdapter.clear()
                        categoryAdapter.add(
                            Category(id = -1, categoryName = "Tất cả", categoryCode =  "")
                        )
                        categoryAdapter.addAll(categories)
                        categoryAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}
