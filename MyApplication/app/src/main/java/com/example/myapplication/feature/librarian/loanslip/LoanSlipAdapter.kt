package com.example.myapplication.feature.librarian.loanslip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemLoanSlipBinding
import com.example.myapplication.domain.model.LoanSlip

class LoanSlipAdapter :
    ListAdapter<LoanSlip, LoanSlipAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemLoanSlipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemLoanSlipBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loanSlip: LoanSlip) {

            binding.tvReceiptNumber.text =
                loanSlip.receiptNumber

            binding.tvBookName.text =
                loanSlip.bookName

            binding.tvBookCode.text =
                "Mã sách: ${loanSlip.bookCode}"

            binding.tvMemberName.text =
                loanSlip.memberName

            binding.tvMemberCardNumber.text =
                "Thẻ: ${loanSlip.memberCardNumber}"

            binding.tvBorrowDate.text =
                "Ngày mượn: ${loanSlip.borrowDate}"

            binding.tvDueDate.text =
                "Hạn trả: ${loanSlip.dueDate}"

            binding.tvState.text =
                when (loanSlip.state) {
                    0 -> "Đang mượn"
                    1 -> "Đã trả"
                    else -> "Không xác định"
                }
        }
    }

    companion object {

        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<LoanSlip>() {

                override fun areItemsTheSame(
                    oldItem: LoanSlip,
                    newItem: LoanSlip
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: LoanSlip,
                    newItem: LoanSlip
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}