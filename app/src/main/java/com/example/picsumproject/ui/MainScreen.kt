package com.example.picsumproject.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.picsumproject.viewmodel.MainViewModel

@Composable
internal fun MainScreen(
    viewModel: MainViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity),
    navController: NavController,
) {
    val uiState by viewModel.mainUiState.collectAsStateWithLifecycle()

    val onImageClick: (String) -> Unit = { id ->
        viewModel.uiAction.invoke(MainUiAction.ImageClick(id))
        navController.navigateToDetail()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.loading) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        } else {
            if (uiState.imageList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(all = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(
                        items = uiState.imageList,
                        key = { _, image -> image.id},
                    ) { _, image ->
                        ImageListItem(
                            image = image,
                            onClick = onImageClick
                        )
                    }
                }
            }
        }
    }
}