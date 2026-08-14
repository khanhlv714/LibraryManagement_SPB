package com.example.myapplication.feature.librarian.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Book

class BookAdapter(
    private var books: List<Book>
) : BaseAdapter() {

    override fun getCount(): Int {
        return books.size
    }

    override fun getItem(position: Int): Book {
        return books[position]
    }

    override fun getItemId(position: Int): Long {
        return books[position].id.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        val view = convertView
            ?: LayoutInflater.from(parent?.context)
                .inflate(
                    R.layout.item_book,
                    parent,
                    false
                )

        val book = getItem(position)

        val tvBookCode = view.findViewById<TextView>(R.id.tvBookCode)
        val tvBookName = view.findViewById<TextView>(R.id.tvBookName)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val tvCategory = view.findViewById<TextView>(R.id.tvCategory)
        val btnDelete = view.findViewById<ImageButton>(R.id.btnDelete)

        tvBookCode.text = book.bookCode
        tvBookName.text = book.bookName
        tvPrice.text = "${book.price} đ"
        tvCategory.text = "Category ID: ${book.categoryId}"

        btnDelete.setOnClickListener {
            // xử lý xóa sau
        }

        return view
    }

    fun updateData(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}