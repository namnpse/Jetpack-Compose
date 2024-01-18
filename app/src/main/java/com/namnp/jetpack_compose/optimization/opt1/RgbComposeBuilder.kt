package com.namnp.jetpack_compose.optimization.opt1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RgbComposeBuilder() {
    val viewModel = viewModel<RgbSelectorViewModel>()
    // a little bit strange -> need only when have really performance issue
    val changeColorLambda = remember<(Color) -> Unit> {
        {
            viewModel.changeColor(it)
        }
    }
    var color by remember {
        mutableStateOf(Color.Red)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        RgbSelector(
            color = viewModel.color,
//            onColorClick = { // create a new instance everytime -> cause all composable that use onColorClick to recompose
//                color = it
//            }
//            onColorClick = changeColorLambda // a little bit strange -> need only when have really performance issue
            onColorClick = viewModel::changeColor // better, reference to just ONLY ONE function,
            // not create a new instance everytime like lambda func
        )
    }

    /*    val viewModel = viewModel<FeedViewModel>()
        val feeds = viewModel.feeds

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CustomGrid(
                feeds = feeds,
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = viewModel::rearrangeFeeds) {
                Text(text = "Shuffle feeds")
            }
        }*/
}