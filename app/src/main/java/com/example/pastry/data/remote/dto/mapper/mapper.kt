package com.example.pastry.data.remote.dto.mapper

import com.example.pastry.data.remote.dto.CategoryDtoItem
import com.example.pastry.data.remote.dto.ProductDtoItem
import com.example.pastry.data.remote.model.Category
import com.example.pastry.data.remote.model.Product

fun ProductDtoItem.toProduct(): Product {
    return Product(
        id = this.id,
        title = this.title,
        image = this.image,
        price = this.price
    )
}

fun CategoryDtoItem.toCategory(): Category {
    return Category(
        image = this.image,
        name = this.name
    )
}