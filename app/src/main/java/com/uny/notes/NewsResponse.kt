package com.uny.notes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class NewsResponse(
	val totalResults: Int? = null,
	val articles: ArrayList<ArticlesItem> ?= null,
	val status: String? = null
):Parcelable
@Parcelize
data class Source(
	val name: String? = null,
	val id: String? = null
):Parcelable

@Parcelize
data class ArticlesItem(
	val publishedAt: String? = null,
	val author: String? = null,
	val urlToImage: String? = null,
	val description: String? = null,
	val source: Source? = null,
	val title: String? = null,
	val url: String? = null,
	val content: String? = null
):Parcelable

