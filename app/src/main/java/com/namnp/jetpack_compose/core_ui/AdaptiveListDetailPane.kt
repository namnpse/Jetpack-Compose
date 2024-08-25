@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.namnp.jetpack_compose.core_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdaptiveListDetailPane() {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavigableListDetailPaneScaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navigator = navigator,
            listPane = {
                ListPane(navigator = navigator)
            },
            detailPane = {
                DetailPane(navigator = navigator)
            },
            extraPane = {
                ExtraPane(navigator = navigator)
            }
        )
    }
}

@Composable
fun ListPane(
    navigator: ThreePaneScaffoldNavigator<Any>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(100) {
            Text(
                "Item $it",
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        navigator.navigateTo(
                            pane = ListDetailPaneScaffoldRole.Detail,
                            content = "Item $it" // T, pass any type of data
                        )
                    }
            )
        }
    }
}

@Composable
fun ThreePaneScaffoldScope.DetailPane(
    navigator: ThreePaneScaffoldNavigator<Any>,
    modifier: Modifier = Modifier
) {
    val content = navigator.currentDestination?.content?.toString() ?: "Select an item"
    AnimatedPane { // optional, used for Animation when navigating
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = content)
            Row {
                AssistChip(
                    onClick = {
                        navigator.navigateTo(
                            pane = ListDetailPaneScaffoldRole.Extra,
                            content = "Option 1" // T, pass any type of data
                        )
                    },
                    label = {
                        Text(text = "Option 1")
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
                AssistChip(
                    onClick = {
                        navigator.navigateTo(
                            pane = ListDetailPaneScaffoldRole.Extra,
                            content = "Option 2" // T, pass any type of data
                        )
                    },
                    label = {
                        Text(text = "Option 2")
                    }
                )
            }
        }
    }
}

@Composable
fun ThreePaneScaffoldScope.ExtraPane(
    navigator: ThreePaneScaffoldNavigator<Any>,
    modifier: Modifier = Modifier
) {
    val content = navigator.currentDestination?.content?.toString() ?: "Select an item"
    AnimatedPane {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(text = content)
        }
    }
}