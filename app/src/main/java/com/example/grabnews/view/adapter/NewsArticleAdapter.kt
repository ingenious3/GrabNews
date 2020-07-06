package com.example.grabnews.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.grabnews.R
import com.example.grabnews.databinding.NewsArticleItemBinding
import com.example.grabnews.db.entity.NewsArticle

class NewsArticleAdapter(private val clickCallback: (String, String) -> Unit) :
    ListAdapter<NewsArticle, DataBoundViewHolder<NewsArticleItemBinding>>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBoundViewHolder<NewsArticleItemBinding> {
        val binding = DataBindingUtil.inflate<NewsArticleItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.news_article_item,
            parent,
            false
        )
        return DataBoundViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DataBoundViewHolder<NewsArticleItemBinding>,
        position: Int
    ) {
        holder.binding.article = getItem(position)
        holder.binding.root.setOnClickListener {
            clickCallback(holder.binding.article!!.url, holder.binding.article!!.description ?: "")
        }
        holder.binding.executePendingBindings()
    }

}

class DataBoundViewHolder<out T : ViewDataBinding> constructor(val binding: T) :
    RecyclerView.ViewHolder(binding.root)


class DiffCallback : DiffUtil.ItemCallback<NewsArticle>() {

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
        return newItem.url == oldItem.url
    }
}