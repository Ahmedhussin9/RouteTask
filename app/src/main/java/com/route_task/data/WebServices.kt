package com.route_task.data

import com.route_task.data.dto.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebServices {
    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>
}