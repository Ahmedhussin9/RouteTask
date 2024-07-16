package com.route_task.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.jawdah.presentation.util.state.UiState
import com.route_task.R
import com.route_task.databinding.FragmentHomeBinding
import com.route_task.presentation.dialog.LoadingDialog
import com.route_task.presentation.ui.BindingFragment
import com.route_task.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeViewModel by viewModels()

    private val adapter: ProductsAdapter by lazy { ProductsAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        fetchData()
        setUpViews()
    }

    private fun setUpViews() {
        binding.rvProducts.adapter = adapter
    }

    private fun fetchData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.products.collect {
                    when(it){
                        is UiState.Loading -> {
                            LoadingDialog.showDialog()
                        }
                        is UiState.Success -> {
                            adapter.submitList(it.data)
                            LoadingDialog.dismissDialog()

                        }
                        is UiState.Error -> {
                            LoadingDialog.dismissDialog()
                            showToast(it.message.toString())
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun getData() {
        viewModel.getProducts()
    }


}