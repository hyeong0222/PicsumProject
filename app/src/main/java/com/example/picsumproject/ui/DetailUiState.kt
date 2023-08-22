package com.example.picsumproject.ui

import com.example.picsumproject.model.PicsumImage

data class DetailUiState(
    val loading: Boolean = false,
    val error: Throwable? = null,

    val image: PicsumImage = PicsumImage(),
)
