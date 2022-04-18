package com.devoTeam.harrypotter.ui.bookDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devoTeam.harrypotter.R
import com.devoTeam.harrypotter.databinding.FragmentBookDetailsBinding

import com.devoTeam.harrypotter.domain.model.Book
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailsFragment : Fragment() {
    private var _binding: FragmentBookDetailsBinding? = null
    private val viewModel: BookDetailsViewModel by viewModels()
    val args: BookDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailsBinding.inflate(layoutInflater, container, false)
        _binding?.book = args.book
        setUpLayout()

        return _binding?.root
    }

    private fun setUpLayout() {
        _binding?.backArrow?.setOnClickListener { findNavController().navigateUp() }
        _binding?.addBtn?.setOnClickListener {
            viewModel.addToCart(book = args.book)
            displayMessageAlert()
        }
    }

    private fun displayMessageAlert() {
        _binding?.let {
            it.root.let { it1 ->
                Snackbar.make(
                    it1,
                    "Livre ajoutée",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }


}