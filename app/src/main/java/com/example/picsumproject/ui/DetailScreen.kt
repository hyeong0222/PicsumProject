package com.example.picsumproject.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.picsumproject.viewmodel.MainViewModel

@Composable
internal fun DetailScreen(
    viewModel: MainViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
) {
    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (detailUiState.loading) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val image = detailUiState.image

                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context).data(image.download_url).build()
                )

                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(250.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    painter = painter,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "ID: ${image.id}"
                )

                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "Author: ${image.author}"
                )

                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "Url: ${image.url}"
                )
            }
        }
    }
}