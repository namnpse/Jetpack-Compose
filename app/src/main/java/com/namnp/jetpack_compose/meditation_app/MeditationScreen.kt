@file:OptIn(ExperimentalFoundationApi::class)

package com.namnp.jetpack_compose.meditation_app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.namnp.jetpack_compose.R
import com.namnp.jetpack_compose.meditation_app.components.BottomNavigationMenu
import com.namnp.jetpack_compose.meditation_app.components.ChipSection
import com.namnp.jetpack_compose.meditation_app.components.CurrentMeditation
import com.namnp.jetpack_compose.meditation_app.components.FeatureItem
import com.namnp.jetpack_compose.meditation_app.components.WelcomeSection
import com.namnp.jetpack_compose.meditation_app.model.BottomMenuModel
import com.namnp.jetpack_compose.meditation_app.model.MeditationFeature
import com.namnp.jetpack_compose.meditation_app.model.features
import com.namnp.jetpack_compose.ui.theme.DeepBlue

@Composable
fun MeditationScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            WelcomeSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(features = features)
        }
        BottomNavigationMenu(items = listOf(
            BottomMenuModel("Home", R.drawable.ic_home),
            BottomMenuModel("Meditate", R.drawable.ic_bubble),
            BottomMenuModel("Sleep", R.drawable.ic_moon),
            BottomMenuModel("Music", R.drawable.ic_music),
            BottomMenuModel("Profile", R.drawable.ic_profile),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<MeditationFeature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features) { meditationFeature ->
                FeatureItem(feature = meditationFeature)
            }
        }
    }
}