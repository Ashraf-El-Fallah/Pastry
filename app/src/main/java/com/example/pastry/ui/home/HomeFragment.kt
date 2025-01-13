package com.example.pastry.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pasrty.databinding.FragmentHomeBinding
import com.example.pastry.utils.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoriesObserver()
        productObserver()
    }

    private fun categoriesObserver() {
        homeViewModel.allCategories.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Loading -> binding.progress.visibility = View.VISIBLE
                is ScreenState.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.categoriesRecyclerView.apply {
                        layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL, false
                        )
                        adapter = CategoriesAdapter().apply {
                            submitList(it.uiData)
                        }
                    }
                }

                is ScreenState.Error -> {
                    binding.progress.visibility = View.GONE
                }
            }
        }
    }

    private fun navigateToProductDetail(productId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(productId)
        findNavController().navigate(action)
    }

    private fun productObserver() {
        homeViewModel.allProducts.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Loading -> binding.progress.visibility = View.VISIBLE
                is ScreenState.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.productsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL, false
                        )
                        adapter = ProductsAdapter(
                            onItemClick = { productId ->
                                productId?.let { navigateToProductDetail(it) }
                            }
                        ).apply {
                            submitList(it.uiData)
                            val (card1Image, card2Image) = homeViewModel.getRandomImagesForCards()
                            Glide.with(requireContext()).load(card1Image)
                                .into(binding.cardImage)
                            Glide.with(requireContext()).load(card2Image)
                                .into(binding.cardImage2)
                        }
                    }
                }

                is ScreenState.Error -> {
                    binding.progress.visibility = View.GONE
                }
            }
        }
    }
}