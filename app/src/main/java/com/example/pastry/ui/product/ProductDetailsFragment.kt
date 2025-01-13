package com.example.pastry.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pasrty.databinding.FragmentDetailsBinding
import com.example.pastry.utils.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private val args: ProductDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productId = args.productId
        productDetailsViewModel.getProductDetailsById(productId)
        productDetailsObserver()
        setUpBackButton()
    }

    private fun productDetailsObserver() {
        productDetailsViewModel.productDetails.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }

                is ScreenState.Success -> {
                    binding.progress.visibility = View.GONE
                    val product = it.uiData
                    binding.apply {
                        productName.text = product.title
                        productPrice.text = product.price.toString()
                        description.text = product.description
                        productType.text = product.category
                        ratingBar.rating = (product.rating?.rate?.toFloat() ?: 0.0).toFloat()
                    }
                    Glide.with(requireContext()).load(product.image).into(binding.productImage)
                }

                is ScreenState.Error -> {
                    binding.progress.visibility = View.GONE
                }
            }
        }
    }

    private fun setUpBackButton() {
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}