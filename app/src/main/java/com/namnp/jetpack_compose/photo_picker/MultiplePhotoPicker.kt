package com.namnp.jetpack_compose.photo_picker

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


@Composable
fun MultiplePhotoPicker() {

    var selectedImageUris by remember {
        // won't work with mutableStateListOf
        mutableStateOf<List<Uri>>(emptyList())
    }

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult( // get a result from an activity in your composable
        contract = ActivityResultContracts.PickMultipleVisualMedia(
            maxItems = 10, // limit 10 images
        ),
        onResult = { uris -> selectedImageUris = uris }
    )

    // Open the photo picker and display the images
    LazyColumn {
        item {
            Button(
                onClick = {
                    multiplePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                        // PickVisualMediaRequest is used to launch a system activity for picking visual media such as images or videos.
                    )
                }
            ) {
                Text(text = "Pick multiple photos")
            }
        }

        items(selectedImageUris) { uri ->
            AsyncImage(
                model = uri,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}