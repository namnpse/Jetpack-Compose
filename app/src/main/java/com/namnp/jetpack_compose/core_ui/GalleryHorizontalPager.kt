package com.namnp.jetpack_compose.core_ui


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GalleryHorizontalPager() {
    val pagerState = rememberPagerState()
    val images = remember {
        mutableStateListOf(
            "https://picsum.photos/id/27/3264/1836",
            "https://picsum.photos/id/28/4928/3264",
            "https://picsum.photos/id/29/4000/2670",
            "https://picsum.photos/id/26/4209/2769",
            "https://picsum.photos/id/25/5000/3333",
            "https://picsum.photos/id/24/4855/1803",
            "https://picsum.photos/id/23/3887/4899",
            "https://picsum.photos/id/22/4434/3729",
        )
    }
    val matrix = remember { ColorMatrix() }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        HorizontalPager(
            modifier = Modifier.padding(it),
            count = images.size,
            state = pagerState,
        ) { index ->
            // 1. add animation when moving, snapped center
            val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffset
            val imageSize by animateFloatAsState(
                targetValue = if (pageOffset != 0.0f) 0.8f else 1.0f, // 0.8f when holding, moving, 1.0f when snapped
                animationSpec = tween(durationMillis = 250),
                label = "Animate Image Size",
            )
            // 2. add grey scale using color filter (grey when moving, normal when snapped)
            LaunchedEffect(key1 = imageSize) {
                matrix.setToSaturation(
                    if (pageOffset != 0.0f) 0.0f else 1.0f
                )
            }

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .graphicsLayer {
                        scaleX = imageSize
                        scaleY = imageSize
                    },
                model = ImageRequest.Builder(LocalContext.current)
                    .data(images[index])
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Gallery Image $index",
                colorFilter = ColorFilter.colorMatrix(matrix)
            )
        }

    }
}