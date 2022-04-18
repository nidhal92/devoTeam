package com.devoTeam.harrypotter.ui.bookList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.devoTeam.harrypotter.databinding.FragmentBooksListBinding
import com.devoTeam.harrypotter.domain.model.Book
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksListFragment : Fragment() {
    private var _binding: FragmentBooksListBinding? = null
    private val viewModel: BookListViewModel by viewModels()
    private var bookList = arrayListOf<Book>()
    private val adapter: BookListAdapter by lazy {
        BookListAdapter(bookList) {
            findNavController().navigate(BooksListFragmentDirections.navigateToDetails(it))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLayout()
        setupBookListObserver()
        viewModel.getCartCount()
        if (bookList.isEmpty())
        viewModel.getBooks()


    }

    private fun setupBookListObserver() {
        viewModel.uiState.observe(
            viewLifecycleOwner
        ) { uiState ->
            showLoading(uiState.isLoading)
            uiState.bookList.let {

                if (it != null&&bookList.isEmpty()) {
                    bookList = it

                    adapter.bookList= bookList
                    adapter.notifyDataSetChanged()
                }
            }
        }
        viewModel.itemsCountUiState.observe(
            viewLifecycleOwner
        ) { uiState ->
            showLoading(uiState.isLoading)
            uiState.count.let {
                _binding?.countCartTV?.apply {
                    isVisible = it?:0 >0
                    text=it.toString()
                }

            }
        }

    }

    private fun showLoading(loading: Boolean) {
        _binding?.progress?.isVisible = loading

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBooksListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    private fun setUpLayout() {
        _binding?.booksRecycler?.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = this@BooksListFragment.adapter
        }
        _binding?.cartBtn?.setOnClickListener {
            findNavController().navigate(BooksListFragmentDirections.navigateToCart())
        }
        _binding?.searchET?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filterList(text.toString())
            }

            override fun afterTextChanged(text: Editable?) {

            }

        })

    }

    private fun filterList(name:String) {
        if (name.isEmpty())
            adapter.bookList=bookList
        else  {

            adapter.bookList= bookList.filter { item-> item.title?.lowercase()?.contains(name.lowercase()) == true} as ArrayList<Book>
        }
        adapter.notifyDataSetChanged()

    }

}