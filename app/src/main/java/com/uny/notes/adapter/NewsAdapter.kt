package com.uny.notes.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uny.notes.ArticlesItem
import com.uny.notes.R
import com.uny.notes.utilities.DateFormatter


class NewsAdapter(private val list: ArrayList<ArticlesItem>) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    var onItemClick: ((ArticlesItem) -> Unit)? = null


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.tv_publishAt)
        val image: ImageView = view.findViewById(R.id.imageVIew)
        val judul: TextView = view.findViewById(R.id.tv_title)
        val description: TextView = view.findViewById(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
        return MyViewHolder(view)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setListNews(news: ArrayList<ArticlesItem>) {
        list.clear()
        list.addAll(news)
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        val value = list[position]
        holder.apply {
            judul.text = value.title.toString()
            description.text = value.description.toString()
            date.text = DateFormatter.formatDate(value.publishedAt.toString())
            Glide.with(itemView.context)
                .load(value.urlToImage)
                .into(image)
            itemView.setOnClickListener {
                onItemClick?.invoke(value)
            }
        }
    }

    override fun getItemCount(): Int = list.size

}