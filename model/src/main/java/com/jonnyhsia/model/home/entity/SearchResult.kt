package com.jonnyhsia.model.home.entity

data class SearchResult<T>(
        val category: String,
        val resultList: List<T>
)