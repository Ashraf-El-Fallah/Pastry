package com.example.pastry.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductDtoItem(
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("rating")
    val ratingDto: RatingDto?,
    @SerializedName("title")
    val title: String?,
)