package com.example.picsumproject.ui

sealed class MainUiAction {
    data class ImageClick(val id: String) : MainUiAction()
}
