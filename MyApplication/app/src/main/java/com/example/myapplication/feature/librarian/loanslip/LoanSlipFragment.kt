package com.example.myapplication.feature.librarian.loanslip

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.base.BaseFragment
import com.example.myapplication.databinding.FragmentLoanSlipBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoanSlipFragment : BaseFragment() {

    private lateinit var binding : FragmentLoanSlipBinding;
    private val viewModel: LoanSlipViewModel by viewModels()
    private lateinit var rcvLoanSlipList : RecyclerView;
    private lateinit var loanSlipAdapter : LoanSlipAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoanSlipBinding.inflate(inflater,container,false);
        rcvLoanSlipList = binding.rcvLoanSlips
        loanSlipAdapter = LoanSlipAdapter()
        rcvLoanSlipList.layoutManager = LinearLayoutManager(requireContext())
        rcvLoanSlipList.adapter =  loanSlipAdapter
        observeUiState()
        viewModel.loadCategories()
        return binding.root
    }
    fun observeUiState(){
        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ){

                viewModel.uiState.collect { state ->

                    loanSlipAdapter.submitList(state.data)

                    if (state.loading) {
                        // Hiện ProgressBar
                    } else {
                        // Ẩn ProgressBar
                    }

                    state.error?.let { message ->
                        showToast(message)
                    }
                }
            }
        }

    }

}