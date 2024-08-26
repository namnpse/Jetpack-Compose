package com.namnp.jetpack_compose.core_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.namnp.jetpack_compose.R

@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    @DrawableRes resId: Int = R.drawable.img_avatar
) {
    Image(
        modifier = modifier
            .size(300.dp)
            .clip(CircleShape) // RoundedCornerShape(30.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape // RoundedCornerShape(30.dp)
            ),
        painter = painterResource(id = resId),
        contentScale = ContentScale.Crop,
        contentDescription = "Circular Image",
    )
}

@Preview
@Composable
private fun CircularImagePreview() {
    CircularImage()
}