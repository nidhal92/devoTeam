package com.devoTeam.harrypotter.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devoTeam.harrypotter.data.remote.response.OfferResponse
import com.devoTeam.harrypotter.databinding.FragmentCartBookBinding
import com.devoTeam.harrypotter.domain.model.PricesObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartBookFragment : Fragment() {

    private var _binding: FragmentCartBookBinding? = null
    private val viewModel: CartBookViewModel by viewModels()
    private val adapter: CartBookAdapter by lazy {
        CartBookAdapter(arrayListOf()) {
            viewModel.deleteItem(it)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLayout()
        setupBookListObserver()
        viewModel.getBooks()
    }

    private fun setupBookListObserver() {
        viewModel.uiState.observe(
            viewLifecycleOwner
        ) { uiState ->
            showLoading(uiState.isLoading)
            uiState.bookList.let {

                if (it != null) {
                    adapter.bookList = it
                    adapter.notifyDataSetChanged()
                    _binding?.emptyView?.isVisible = it.isEmpty()
                    _binding?.buyBtn?.isEnabled = it.isNotEmpty()

                }
            }
        }
        viewModel.offersUiState.observe(
            viewLifecycleOwner, Observer {
                showLoading(it.isLoading)
                if (it.offers != null) {
                    findNavController().navigate(
                        CartBookFragmentDirections.actionCartFragmentToOfferFragment(
                            PricesObject(
                                getTotalPrices(),
                                getBestOffer(it.offers),
                                calculateFinalPrice(getTotalPrices(), getBestOffer(it.offers))
                            )
                        )
                    )
                    viewModel.resetData()
                }
            }
        )

    }

    private fun showEmptyList() {

    }

    private fun calculateFinalPrice(totalPrices: String, bestOffer: String): String {
        return (totalPrices.toInt() - bestOffer.toInt()).toString()
    }

    private fun getBestOffer(offers: OfferResponse?): String {
        var valueOffer = offers?.offers?.get(0)?.value
        offers?.offers?.forEach {
            if (it.value.toInt() > valueOffer?.toInt() ?: 0)
                valueOffer = it.value
        }
        return valueOffer.toString()
    }

    private fun getTotalPrices(): String {
        var total = 0
        adapter.bookList.forEach { total += it.price.toInt() }
        return total.toString()
    }

    private fun showLoading(loading: Boolean) {
        _binding?.progress?.isVisible = loading
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBookBinding.inflate(layoutInflater, container, false)
        setUpLayout()
        return _binding?.root
    }

    private fun setUpLayout() {
        _binding?.booksRecycler?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CartBookFragment.adapter
        }
        _binding?.buyBtn?.setOnClickListener {
            viewModel.getOffers()
        }
        _binding?.backArrow?.setOnClickListener {
            findNavController().navigateUp()
        }
    }


}