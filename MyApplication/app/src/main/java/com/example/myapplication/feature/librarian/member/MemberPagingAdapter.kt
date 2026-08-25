package com.example.myapplication.feature.librarian.member

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemMemberBinding
import com.example.myapplication.domain.model.Member

class MemberPagingAdapter :
    PagingDataAdapter<Member, MemberPagingAdapter.MemberViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemberViewHolder {

        val binding = ItemMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MemberViewHolder,
        position: Int
    ) {
        val member = getItem(position)

        if (member != null) {
            holder.bind(member)
        }
    }

    class MemberViewHolder(
        private val binding: ItemMemberBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(member: Member) {
            binding.tvMemberName.text = member.name
            binding.tvCardNumber.text = "Mã thẻ: ${member.cardNumber}"
        }
    }

    companion object {

        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<Member>() {

                override fun areItemsTheSame(
                    oldItem: Member,
                    newItem: Member
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Member,
                    newItem: Member
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}