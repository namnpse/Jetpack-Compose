@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.namnp.jetpack_compose.animation.shared_transition

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.namnp.jetpack_compose.R

@Composable
fun SharedTransitionScope.ListTrainScreen(
    onItemClick: (Int, String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val trainImages = listOf(
        R.drawable.train,
        R.drawable.train_wagon,
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(trainImages) { index, resId ->
            val text = "Train ${index+1}"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(resId, text) }
            ) {
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(16 / 9f)
                        .weight(1f)
                        .sharedElement(
                            state = rememberSharedContentState(key = "train/$resId"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(1000)
                            }
                        )
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = text,
                    modifier = Modifier
                        .weight(1f)
                        .sharedElement(
                            state = rememberSharedContentState(key = "train/$text"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(1000)
                            }
                        )
                )
            }
        }
    }
}