package com.uny.notes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.uny.notes.ArticlesItem
import com.uny.notes.R

class NewsAdapter(private val list: ArrayList<ArticlesItem>) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageVIew)
        val judul: TextView = view.findViewById(R.id.tv_title)
        val description: TextView = view.findViewById(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)

        return MyViewHolder(view)
    }
    fun setListNews(news: ArrayList<ArticlesItem>) {
        list.clear()
        list.addAll(news)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        val value = list[position]
        holder.apply {
            judul.text = value.title.toString()
            description.text = value.description.toString()
            Glide.with(itemView.context)
                .load(value.urlToImage)
                .into(image)
        }
    }

    override fun getItemCount(): Int = list.size

}