package com.example.myapplication.feature.librarian.member

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Member

class MemberAdapter(val context: Context, var list: List<Member>) : RecyclerView.Adapter<MemberAdapter.ViewHolder>() {

    fun updateData(list: List<Member>) {
        this.list = list;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)

        val contactView: View = inflater.inflate(R.layout.item_member, parent, false)

        val viewHolder = ViewHolder(contactView)
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.tvMemberName.text = list[position].name
        viewHolder.tvCardNumber.text = list[position].cardNumber

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder : RecyclerView.ViewHolder {
        lateinit var tvMemberName: TextView;
        lateinit var tvCardNumber: TextView;

        constructor(itemView: View) : super(itemView) {
            tvMemberName = itemView.findViewById<TextView>(R.id.tvMemberName)
            tvCardNumber = itemView.findViewById<TextView>(R.id.tvCardNumber)

        }
    }
}
