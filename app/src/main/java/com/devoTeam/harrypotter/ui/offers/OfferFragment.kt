package com.devoTeam.harrypotter.ui.offers

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devoTeam.harrypotter.R
import com.devoTeam.harrypotter.databinding.FragmentBookDetailsBinding
import com.devoTeam.harrypotter.databinding.FragmentOfferBinding
import com.devoTeam.harrypotter.ui.bookDetails.BookDetailsFragmentArgs
import com.devoTeam.harrypotter.ui.bookDetails.BookDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class OfferFragment : Fragment() {
    private var _binding: FragmentOfferBinding? = null
    private val viewModel: OfferViewModel by viewModels()
    val args: OfferFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferBinding.inflate(layoutInflater, container, false)
        _binding?.offer = args.prices
        setUpLayout()

        return _binding?.root
    }

    private fun setUpLayout() {
        _binding?.backArrow?.setOnClickListener { findNavController().navigateUp() }
        _binding?.buyBtn?.setOnClickListener {
            viewModel.deleteAll()
            showSuccess()
            // findNavController().navigateUp()
        }
        _binding?.animationView?.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                //Do nothing
            }

            override fun onAnimationEnd(animation: Animator?) {
                CoroutineScope(Dispatchers.IO).launch {
                    delay(TimeUnit.SECONDS.toMillis(1))
                    withContext(Dispatchers.Main) {
                       findNavController().navigate(OfferFragmentDirections.actionOfferFragmentToFragmentBookList())
                    }
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
    }

    private fun showSuccess() {
        _binding?.progress?.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            withContext(Dispatchers.Main) {
                _binding?.successLayout?.isVisible = true
            }
        }
    }


}