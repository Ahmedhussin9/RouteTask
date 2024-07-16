package com.route_task.domain.repos

import com.jawdah.presentation.util.state.Resource
import com.route_task.data.dto.ProductsResponse
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts():Flow<Resource<ProductsResponse>>
}