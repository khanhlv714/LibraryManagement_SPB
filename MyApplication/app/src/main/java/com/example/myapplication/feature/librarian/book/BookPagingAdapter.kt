package com.example.myapplication.feature.librarian.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Book

class BookPagingAdapter :
    PagingDataAdapter<Book, BookPagingAdapter.BookViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int
    ) {
        val book = getItem(position)

        if (book != null) {
            holder.bind(book)
        }
    }

    class BookViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvBookName: TextView =
            itemView.findViewById(R.id.tvBookName)

        private val tvBookCode: TextView =
            itemView.findViewById(R.id.tvBookCode)

        private val tvPrice: TextView =
            itemView.findViewById(R.id.tvPrice)

        fun bind(book: Book) {
            tvBookName.text = book.bookName
            tvBookCode.text = book.bookCode
            tvPrice.text = book.price.toString()
        }
    }

    companion object {

        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<Book>() {

                override fun areItemsTheSame(
                    oldItem: Book,
                    newItem: Book
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Book,
                    newItem: Book
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}