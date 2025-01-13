package com.example.pastry.data.remote.dto.mapper

import com.example.pastry.data.remote.dto.CategoryDtoItem
import com.example.pastry.data.remote.dto.ProductDtoItem
import com.example.pastry.data.remote.dto.RatingDto
import com.example.pastry.data.remote.model.Category
import com.example.pastry.data.remote.model.Product
import com.example.pastry.data.remote.model.ProductDetails
import com.example.pastry.data.remote.model.Rating

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
        name = this.name,
        id = this.id
    )
}

fun ProductDtoItem.toProductDetails(): ProductDetails {
    return ProductDetails(
        category = this.category,
        description = this.description,
        id = this.id,
        image = this.image,
        price = this.price,
        rating = this.ratingDto?.toRating(),
        title = this.title
    )
}

fun RatingDto.toRating(): Rating {
    return Rating(
        count = this.count,
        rate = this.rate
    )
}