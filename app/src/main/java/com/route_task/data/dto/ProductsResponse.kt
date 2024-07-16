package com.route_task.data.dto

import android.os.Parcelable
import com.route_task.domain.ProductsModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
) : Parcelable

@Parcelize
data class Product(
    val availabilityStatus: String?,
    val brand: String?,
    val category: String?,
    val description: String?,
    val dimensions: Dimensions?,
    val discountPercentage: Double?,
    val id: Int?,
    val images: List<String>?,
    val meta: Meta?,
    val minimumOrderQuantity: Int?,
    val price: Double?,
    val rating: Double?,
    val returnPolicy: String?,
    val reviews: List<Review>?,
    val shippingInformation: String?,
    val sku: String?,
    val stock: Int?,
    val tags: List<String?>,
    val thumbnail: String?,
    val title: String?,
    val warrantyInformation: String?,
    val weight: Int
) : Parcelable{
    fun getDiscountPrice(): Double {
        return price?.minus((price * discountPercentage!! / 100)) ?: 0.0
    }
    fun toProductModel(): ProductsModel{
        return ProductsModel(
            brand = brand?:"",
            category = category?:"",
            description = description?:"",
            realPrice = getDiscountPrice(),
            fake = price?:0.0,
            id = id?:-1,
            images = images?: emptyList(),
            minimumOrderQuantity = minimumOrderQuantity?:-1,
            rating = rating?:0.0,
        )
    }
}

@Parcelize
data class Meta(
    val barcode: String,
    val createdAt: String,
    val qrCode: String,
    val updatedAt: String
) : Parcelable

@Parcelize

data class Dimensions(
    val depth: Double,
    val height: Double,
    val width: Double
) : Parcelable

@Parcelize

data class Review(
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String
) : Parcelable