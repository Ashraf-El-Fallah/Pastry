package com.example.pastry.ui.home.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pasrty.databinding.ItemProductBinding
import com.example.pastry.data.remote.model.Category
import com.example.pastry.utils.loadImage

class CategoriesDetailsAdapter :
    ListAdapter<Category, CategoriesDetailsAdapter.CategoriesDetailsViewHolder>(CategoriesDiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CategoriesDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return CategoriesDetailsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CategoriesDetailsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class CategoriesDetailsViewHolder(
        private val binding: ItemProductBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.apply {
                productsImage.loadImage(category.image)
                productsName.text = category.name
                addButton.visibility = View.GONE
            }
        }
    }

    private class CategoriesDiffCallBack : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}
