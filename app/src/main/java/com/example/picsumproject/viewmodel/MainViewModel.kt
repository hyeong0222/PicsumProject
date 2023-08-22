package com.example.picsumproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsumproject.model.PicsumImage
import com.example.picsumproject.repository.MainRepository
import com.example.picsumproject.ui.DetailUiState
import com.example.picsumproject.ui.MainUiAction
import com.example.picsumproject.ui.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState = _detailUiState.asStateFlow()

    private val actionShared = MutableSharedFlow<MainUiAction>()

    val uiAction: (MainUiAction) -> Unit = { action ->
        viewModelScope.launch { actionShared.emit(action) }
    }

    init {
        initObservers()
        fetchImageList()
    }

    private fun initObservers() {
        viewModelScope.launch {
            actionShared.collectLatest { action ->
                when (action) {
                    is MainUiAction.ImageClick -> {
                        requestImageDetail(id = action.id)
                    }
                }
            }
        }
    }

    private fun fetchImageList() {
        viewModelScope.launch {
            mainRepository.getImageList()
                .onStart { setLoading() }
                .catch { error -> setError(error = error) }
                .collect { imageList ->
                    setUi(imageList = imageList)
                }
        }
    }

    private fun requestImageDetail(id: String) {
        viewModelScope.launch {
            mainRepository.getImageDetail(id = id)
                .onStart { setDetailLoading() }
                .catch { error -> setError(error = error) }
                .collect { image ->
                    setDetailUi(image = image)
                }
        }
    }

    private fun setUi(imageList: List<PicsumImage>) {
        _mainUiState.update { state -> state.copy(imageList = imageList, loading = false) }
    }

    private fun setLoading() {
        _mainUiState.update { state -> state.copy(loading = true) }
    }

    private fun setError(error: Throwable) {
        _mainUiState.update { state -> state.copy(error = error, loading = false) }
    }

    private fun setDetailUi(image: PicsumImage) {
        _detailUiState.update { state -> state.copy(image = image, loading = false) }
    }

    private fun setDetailLoading() {
        _detailUiState.update { state -> state.copy(loading = true) }
    }

    private fun setDetailError(error: Throwable) {
        _detailUiState.update { state -> state.copy(error = error, loading = false) }
    }
}