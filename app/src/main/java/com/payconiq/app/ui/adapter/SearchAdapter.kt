package com.payconiq.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.payconiq.app.R
import com.payconiq.app.databinding.UserItemBinding
import com.payconiq.app.models.GithubSearchModel

class SearchAdapter(private val onItemClickListener: OnItemClickListener) : PagingDataAdapter<GithubSearchModel, SearchAdapter.UserViewHolder>(USER_COMPARATOR) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    inner class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {


        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null)
                        onItemClickListener.onItemClick(item)
                }
            }
        }

        fun bind(item: GithubSearchModel) {

            with(binding)
            {
                apply {
                    Glide.with(binding.imgView)
                            .load(item.avatarUrl)
                            .centerCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(R.drawable.ic_error)
                            .into(binding.imgView)
                }
                binding.txtTags.text = item.login
                binding.txtType.text = item.type
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(user: GithubSearchModel)

    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<GithubSearchModel>() {
            override fun areItemsTheSame(oldItem: GithubSearchModel, newItem: GithubSearchModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: GithubSearchModel, newItem: GithubSearchModel) = oldItem == newItem
        }
    }
}