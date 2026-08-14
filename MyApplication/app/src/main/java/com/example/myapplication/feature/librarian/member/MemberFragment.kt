package com.example.myapplication.feature.librarian.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.base.BaseFragment
import com.example.myapplication.databinding.FragmentMemberBinding
import com.example.myapplication.domain.model.Member
import com.example.myapplication.feature.librarian.category.CategoryAdapter
import com.example.myapplication.feature.librarian.category.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue


@AndroidEntryPoint
class MemberFragment : BaseFragment() {

    private val viewModel: MemberViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : MemberAdapter
    private lateinit var binding: FragmentMemberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemberBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rcvMembers;
        adapter = MemberAdapter(requireContext(),listOf())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter =  adapter;

        observeUiState()

        // Gọi API thông qua ViewModel
        viewModel.loadMembers()
    }

    fun observeUiState(){
        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ){

                viewModel.uiState.collect { state ->

                    Log.d("size :",""+state.memberList.size);
                    adapter.updateData(state.memberList)

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