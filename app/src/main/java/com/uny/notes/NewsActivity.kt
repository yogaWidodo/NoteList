package com.uny.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.uny.notes.adapter.NewsAdapter
import com.uny.notes.databinding.ActivityNewsBinding
import com.uny.notes.viewmodel.NewsViewModel

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var adapter: NewsAdapter
    private val viewModel by viewModels<NewsViewModel>()
    val list = ArrayList<ArticlesItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.setHasFixedSize(true)
        adapter = NewsAdapter(list)

        binding.apply {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchView.hide()
                    searchUser()
                    false
                }
            searchUser()
        }
        viewModel.getSearchUser().observe(this){
            adapter.setListNews(it)
        }
    }

    private fun searchUser() {
        binding.apply {
            var query = searchView.text.toString()
            if (query.isEmpty()) {
                query = "Bitcoin"
            }
            viewModel.getNews(query, binding.rvNews, adapter)
        }
    }
}