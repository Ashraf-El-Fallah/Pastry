package com.example.pastry.ui.home.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pasrty.databinding.FragmentCategoriesBinding
import com.example.pastry.ui.home.HomeViewModel
import com.example.pastry.utils.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoriesObserver()
    }

    private fun categoriesObserver() {
        homeViewModel.allCategories.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Loading -> binding.progress.visibility = View.VISIBLE
                is ScreenState.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.categoriesRecyclerView.apply {
                        layoutManager = GridLayoutManager(
                            requireContext(),
                            3
                        )
                        adapter = CategoriesDetailsAdapter().apply {
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
}