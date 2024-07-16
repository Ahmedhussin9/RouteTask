package com.route_task.data.repos

import com.jawdah.presentation.util.state.ApiState
import com.jawdah.presentation.util.state.Resource
import com.route_task.data.WebServices
import com.route_task.data.dto.ProductsResponse
import com.route_task.domain.repos.ProductsRepository
import com.route_task.util.toResultFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : ProductsRepository {
    override suspend fun getProducts(): Flow<Resource<ProductsResponse>> {
        return flow {
            val result = toResultFlow { webServices.getProducts() }
            result.collect() {
                when (it) {
                    is ApiState.Loading -> {
                        emit(Resource.Loading())
                    }

                    is ApiState.Success -> {
                        emit(Resource.Success(it.data))
                    }

                    is ApiState.Error -> {
                        emit(Resource.Error(it.message!!))
                    }
                }
            }
        }
    }

}