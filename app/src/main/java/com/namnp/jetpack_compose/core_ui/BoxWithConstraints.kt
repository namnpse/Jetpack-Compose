package com.namnp.jetpack_compose.core_ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BoxWithConstraintsComposable() {

    val productItems = remember {
        mutableStateListOf<Product>()
    }
    for (id in 0 until 20) {
        productItems.add(
            element = Product(
                id = id,
                image = "https://picsum.photos/id/$id/300/300",
                title = "#$id Lorem Ipsum",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                badges = listOf(
                    Icons.Default.Check,
                    Icons.Default.Edit,
                    Icons.Default.Face,
                    Icons.Default.Email,
                    Icons.AutoMirrored.Filled.List,
                    Icons.Default.Home
                )
            )
        )
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = productItems, key = { it.id }) {
            ProductItemCard(data = it)
        }
    }
}

@Composable
fun ProductItemCard(data: Product) {
    Row(modifier = Modifier.border(width = 1.dp, color = Color.LightGray)) {
        AsyncImage(
            modifier = Modifier
                .weight(1f),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = data.image)
                .crossfade(true)
                .build(),
            contentDescription = "Product Card Image",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        BoxWithConstraints(
            modifier = Modifier
                .weight(1.5f)
                .padding(vertical = 12.dp)
        ) {
            AdaptiveContent(data = data)
        }
    }
}

@Composable
fun BoxWithConstraintsScope.AdaptiveContent(data: Product) {
    val badgeSize = 24.dp
    val padding = 24.dp
    val numberOfBadgesToShow = maxWidth.div(badgeSize + padding).toInt().minus(1)
    val remainingBadges = data.badges.size - numberOfBadgesToShow

    Log.d("ProductItem", "${this.maxWidth}")
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = data.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = data.description,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
            maxLines = if (this@AdaptiveContent.maxWidth > 250.dp) 10 else 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(space = padding)) {
            data.badges.take(numberOfBadgesToShow).forEach {
                Icon(
                    modifier = Modifier.size(badgeSize),
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = it,
                    contentDescription = "Badge Icon"
                )
            }
            if (remainingBadges > 0) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .padding(6.dp)
                ) {
                    Text(
                        text = "+$remainingBadges",
                        style = TextStyle(fontSize = MaterialTheme.typography.labelMedium.fontSize)
                    )
                }
            }
        }
    }
}

data class Product(
    val id: Int,
    val image: String,
    val title: String,
    val description: String,
    val badges: List<ImageVector>
)