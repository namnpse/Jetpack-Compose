package com.namnp.jetpack_compose.core_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun LazyGridCompose() {
    val gridState = rememberLazyGridState(
        initialFirstVisibleItemIndex = 99 // scroll to item 99 at first
    )
    val coroutineScope = rememberCoroutineScope()

    LazyVerticalGrid(
        state = gridState,
//        columns = GridCells.Fixed(5), // always 5 items in a row, width will be stretch when rotate device
        columns = GridCells.Adaptive(minSize = 100.dp) // each item size has min 100dp, number of items different when rotate device, width will be the same
    )
    {
        items(100) { index ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Blue)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        coroutineScope.launch {
                            gridState.animateScrollToItem(99) // scroll to position
                        }
                    }
                ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Item ${index + 1}"
                )
            }
        }
    }
}