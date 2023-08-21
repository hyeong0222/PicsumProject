package com.example.picsumproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsumproject.model.Image
import com.example.picsumproject.repository.MainRepository
import com.example.picsumproject.ui.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    init {
        fetchImageList()
    }

    private fun fetchImageList() {
        viewModelScope.launch {
            mainRepository.getPhotoList()
                .onStart { setLoading() }
                .catch { error -> setError(error = error) }
                .collect { imageList ->
                    setUi(imageList = imageList)
                }
        }
    }

    private fun setUi(imageList: List<Image>) {
        _mainUiState.update { state -> state.copy(imageList = imageList, loading = false) }
    }

    private fun setLoading() {
        _mainUiState.update { state -> state.copy(loading = true) }
    }

    private fun setError(error: Throwable) {
        _mainUiState.update { state -> state.copy(error = error, loading = false) }
    }
}