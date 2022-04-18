package com.devoTeam.harrypotter.ui.bookList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devoTeam.harrypotter.databinding.BookItemBinding
import com.devoTeam.harrypotter.domain.model.Book

class BookListAdapter(var bookList: ArrayList<Book>,val adapterOnClick : (Book) -> Unit) :
    RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            BookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )



    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
        holder.itemView.setOnClickListener { adapterOnClick(bookList[position]) }
    }

    override fun getItemCount(): Int = bookList.size
    class BookViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.book = book
        }

    }
}