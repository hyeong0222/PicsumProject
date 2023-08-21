package com.example.picsumproject.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.picsumproject.viewmodel.MainViewModel

@Composable
internal fun MainScreen(viewModel: MainViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)) {
    val uiState by viewModel.mainUiState.collectAsStateWithLifecycle()

    if (uiState.imageList.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ImageNotEmpty"
            )
        }
    }
}