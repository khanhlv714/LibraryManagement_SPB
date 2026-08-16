package com.example.myapplication.feature.librarian.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.CategoryWithBookCount

class CategoryAdapter(var list: List<CategoryWithBookCount>) : BaseAdapter() {

    fun updateData(newList: List<CategoryWithBookCount>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): CategoryWithBookCount? {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {

        val view = p1
            ?: LayoutInflater.from(p2?.context)
                .inflate(
                    R.layout.item_category,
                    p2,
                    false
                )

        val category = getItem(p0)

        val tvCateogoryNameandCode = view.findViewById<TextView>(R.id.tvCateogoryNameandCode)
        val tvAmountBook = view.findViewById<TextView>(R.id.tvAmountBook)

        tvCateogoryNameandCode.text = category!!.category.categoryName +" - "+category.category.categoryCode
        tvAmountBook.text = "Books : "+category.amountBook

        return view
    }
}