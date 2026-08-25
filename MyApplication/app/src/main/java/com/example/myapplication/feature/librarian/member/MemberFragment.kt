package com.example.myapplication.feature.librarian.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.core.base.BaseFragment
import com.example.myapplication.databinding.FragmentMemberBinding
import com.example.myapplication.domain.model.Member
import com.example.myapplication.domain.model.MemberLoanStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MemberFragment : BaseFragment() {

    private val viewModel: MemberViewModel by viewModels()

    private lateinit var binding: FragmentMemberBinding
    private lateinit var adapter: MemberPagingAdapter
    private lateinit var statusAdapter: ArrayAdapter<MemberLoanStatus>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMemberBinding.inflate(
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

        setupRecyclerView()
        setupStatus()
        eventListener()
        observeUiState()
    }


    private fun setupRecyclerView() {

        adapter = MemberPagingAdapter()

        binding.rcvMembers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MemberFragment.adapter
        }
    }


    // =========================
    // Status Spinner
    // =========================

    private fun setupStatus() {

        statusAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mutableListOf<MemberLoanStatus>()
        )

        statusAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerStatus.adapter = statusAdapter

        statusAdapter.add(
            MemberLoanStatus.NO_BORROWING
        )
        statusAdapter.add(
            MemberLoanStatus.DUE
        )
        statusAdapter.add(
            MemberLoanStatus.BORROWING
        )
        statusAdapter.add(
            MemberLoanStatus.ALL
        )

    }


    // =========================
    // Event
    // =========================

    private fun eventListener() {

        // Search
        binding.edtSearch.doAfterTextChanged { text ->
            viewModel.search(
                text?.toString().orEmpty()
            )
        }

        // Status
        binding.spinnerStatus.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val item =
                        parent?.getItemAtPosition(position)
                                as? MemberLoanStatus
                            ?: return

                    viewModel.setMemberStatus(item)
                }


                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {}
            }
    }


    // =========================
    // Observe
    // =========================

    private fun observeUiState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                launch {
                    viewModel.memberFilter.collect { page ->
                        adapter.submitData(page)
                    }
                }

            }
        }
    }
}