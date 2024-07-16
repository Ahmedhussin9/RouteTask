package com.route_task.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jawdah.presentation.util.state.Resource
import com.jawdah.presentation.util.state.UiState
import com.route_task.data.dto.ProductsResponse
import com.route_task.domain.ProductsModel
import com.route_task.domain.repos.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: ProductsRepository
):ViewModel() {

    private var _products = MutableStateFlow<UiState<List<ProductsModel>>>(UiState.Empty())
    val products:StateFlow<UiState<List<ProductsModel>>> = _products




    fun getProducts(){
        viewModelScope.launch (Dispatchers.IO){
            repository.getProducts().collect(){ it ->
                when (it){
                    is Resource.Loading -> {
                        _products.value = UiState.Loading()
                    }
                    is Resource.Success -> {
                        val data = it.data
                        val list = ArrayList<ProductsModel>()
                        data?.products?.forEach {
                            val product = it.toProductModel()
                            list.add(product)
                        }
                        _products.value = UiState.Success(list)

                    }
                    is Resource.Error -> {

                    }

                }
            }
        }
    }
}