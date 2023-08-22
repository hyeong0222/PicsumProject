package com.example.picsumproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.picsumproject.model.PicsumImage

@Composable
internal fun ImageListItem(
    image: PicsumImage,
    onClick: (String) -> Unit,
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick.invoke(image.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context).data(image.download_url).build()
            )

            Image(
                modifier = Modifier
                    .padding( 10.dp)
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painter,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(text = image.author)
        }
    }
}