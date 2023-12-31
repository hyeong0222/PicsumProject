package com.example.picsumproject.ui

import com.example.picsumproject.model.PicsumImage

data class MainUiState(
    val loading: Boolean = false,
    val error: Throwable? = null,

    val imageList: List<PicsumImage> = listOf(),
)
