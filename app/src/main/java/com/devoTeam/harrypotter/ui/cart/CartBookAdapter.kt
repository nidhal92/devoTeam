package com.devoTeam.harrypotter.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devoTeam.harrypotter.databinding.BookItemBinding
import com.devoTeam.harrypotter.databinding.CartBookItemBinding
import com.devoTeam.harrypotter.domain.model.Book

class CartBookAdapter(var bookList: List<Book>, val adapterOnClick : (Book) -> Unit) :
    RecyclerView.Adapter<CartBookAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            CartBookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )



    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
        holder.binding.deleteBtn.setOnClickListener {
            adapterOnClick(bookList[position])

                //bookList.(bookList[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = bookList.size
    class BookViewHolder(val binding: CartBookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.book = book
        }

    }
}