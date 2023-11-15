package com.uny.notes

import android.content.Intent
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
        adapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("news", it)
            startActivity(intent)
        }

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
        viewModel.getSearchUser().observe(this) { list ->
            adapter.setListNews(list)
            isShowLoading(false)
        }

    }

    private fun searchUser() {
        binding.apply {
            var query = searchView.text.toString()
            isShowLoading(true)
            if (query.isEmpty()) {
                query = "Breaking News"
            }
            viewModel.getNews(query, binding.rvNews, adapter)
        }
    }

    private fun isShowLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = android.view.View.VISIBLE
        } else {
            binding.progressBar.visibility = android.view.View.GONE
        }
    }
}