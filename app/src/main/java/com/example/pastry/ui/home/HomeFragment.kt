package com.example.pastry.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
}