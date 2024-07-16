package com.route_task.domain

import com.route_task.data.dto.Dimensions
import com.route_task.data.dto.Meta
import com.route_task.data.dto.Review

data class ProductsModel(
    val brand: String,
    val category: String,
    val description: String,
    val fake: Double,
    val realPrice: Double,
    val id: Int,
    val images: List<String>,
    val minimumOrderQuantity: Int,
    val rating: Double,
)