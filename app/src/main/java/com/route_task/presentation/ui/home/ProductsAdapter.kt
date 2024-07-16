package com.route_task.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route_task.databinding.ItemProductBinding
import com.route_task.domain.ProductsModel
import javax.inject.Inject

class ProductsAdapter @Inject constructor():
    ListAdapter<ProductsModel,ProductsAdapter.ProductsViewHolder>(PRODUCT_COMPARATOR)
{
    inner class ProductsViewHolder(val binding:ItemProductBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:ProductsModel){
            binding.apply {
                tvTitle.text = item.brand
                tvSubtitle.text = item.description
                tvRealPrice.text = item.realPrice.toString()
                tvFake.text = item.fake.toString()
                tvReview.text = item.rating.toString()
                Glide.with(itemView).load(item.images[0]).into(ivProductImage)
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<ProductsModel>() {
        override fun areItemsTheSame(oldItem: ProductsModel, newItem: ProductsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductsModel, newItem: ProductsModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ProductsViewHolder (ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))






    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }

    }
    companion object {
        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<ProductsModel>() {
            override fun areItemsTheSame(
                oldItem: ProductsModel,
                newItem: ProductsModel
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: ProductsModel,
                newItem: ProductsModel
            ) = oldItem == newItem
        }
    }


}