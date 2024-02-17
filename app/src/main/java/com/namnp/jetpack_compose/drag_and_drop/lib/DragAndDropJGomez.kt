package com.namnp.jetpack_compose.drag_and_drop.lib

//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.animation.core.animateIntAsState
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.key
//import androidx.compose.runtime.mutableStateMapOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.rotate
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.zIndex
//import com.jgomezdev.draganddropcompose.SlideState
//import com.jgomezdev.draganddropcompose.dragAndDrop
//import com.namnp.jetpack_compose.R
//import kotlinx.coroutines.launch
//
//data class People(
//    val name: String = "",
//)
//
//val peopleList = mutableListOf(
//    People(name = "Martin Jenkins"),
//    People(name = "Jacquelyn Keith"),
//    People(name = "Lavern Chen")
//)
//
//@OptIn(
//    ExperimentalMaterial3Api::class,
//    ExperimentalAnimationApi::class
//)
//@Composable
//fun DragAndDropJGomez() {
//    val slideStates = remember {
//        mutableStateMapOf<People, SlideState>()
//            .apply {
//                peopleList.map { example ->
//                    example to SlideState.NONE
//                }.toMap().also {
//                    putAll(it)
//                }
//            }
//    }
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = "Drag and Drop In Compose")
//                }
//            )
//        }
//    ) { innerPadding ->
//        ExampleList(
//            modifier = Modifier.padding(innerPadding),
//            peopleList = peopleList,
//            slideStates = slideStates,
//            updateSlideState = { people, slideState ->
//                // Handle slide state updates here
//            },
//            updateItemPosition = { currentIndex, destinationIndex ->
//                // Handle item position updates here
//            }
//        )
//    }
//}
//
//@ExperimentalAnimationApi
//@Composable
//fun ExampleList(
//    modifier: Modifier,
//    peopleList: MutableList<People>,
//    slideStates: Map<People, SlideState>,
//    updateSlideState: (people: People, slideState: SlideState) -> Unit,
//    updateItemPosition: (currentIndex: Int, destinationIndex: Int) -> Unit
//) {
//    val lazyListState = rememberLazyListState()
//    LazyColumn(
//        state = lazyListState,
////        modifier = modifier.padding(top = dimensionResource(id = R.dimen.list_top_padding))
//        modifier = modifier.padding(top = 16.dp)
//    ) {
//        items(peopleList.size) { index ->
//            val example = peopleList.getOrNull(index)
//            if (example != null) {
//                key(example) {
//                    val slideState = slideStates[example] ?: SlideState.NONE
//                    BasicExample(
//                        peoble = example,
//                        peopleList = peopleList,
//                        slideState = slideState,
//                        updateSlideState = updateSlideState,
//                        updateItemPosition = updateItemPosition
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BasicExample(
//    peoble: People,
//    peopleList: MutableList<People>,
//    slideState: SlideState,
//    updateSlideState: (shoesArticle: People, slideState: SlideState) -> Unit,
//    updateItemPosition: (currentIndex: Int, destinationIndex: Int) -> Unit
//) {
//    val isDragged = remember { mutableStateOf(false) }
//    val zIndex = if (isDragged.value) 1.0f else 0.0f
//    val rotation = if (isDragged.value) -5.0f else 0.0f
//    val elevation = if (isDragged.value) 8.dp else 0.dp
//
//    val currentIndex = remember { mutableStateOf(0) }
//    val destinationIndex = remember { mutableStateOf(0) }
//    val isPlaced = remember { mutableStateOf(false) }
//
////    val itemHeightDp = dimensionResource(id = R.dimen.image_size)
//    val itemHeightDp = 100.dp
//    var itemHeight: Int
//
//    with(LocalDensity.current) {
//        itemHeight = itemHeightDp.toPx().toInt()
//    }
//
//    val verticalTranslation by animateIntAsState(
//        targetValue = when (slideState) {
//            SlideState.UP -> -itemHeight
//            SlideState.DOWN -> itemHeight
//            else -> 0
//        },
//        label = "",
//    )
//
//    LaunchedEffect(isPlaced.value) {
//        if (isPlaced.value) {
//            launch {
//                if (currentIndex.value != destinationIndex.value) {
//                    updateItemPosition(currentIndex.value, destinationIndex.value)
//                }
//                isPlaced.value = false
//            }
//        }
//    }
//    Column {
//        Box(
//            Modifier
//                .dragAndDrop(
//                    peoble,
//                    peopleList,
//                    itemHeight,
//                    updateSlideState,
//                    isDraggedAfterLongPress = false,
//                    { isDragged.value = true },
//                    { cIndex, dIndex ->
//                        isDragged.value = false
//                        isPlaced.value = true
//                        currentIndex.value = cIndex
//                        destinationIndex.value = dIndex
//                    }
//                )
//                .padding(horizontal = 16.dp)
//                .offset { IntOffset(0, verticalTranslation) }
//                .zIndex(zIndex)
//                .rotate(rotation)
//
//        ) {
//            Column(
//                modifier = Modifier
//                    .shadow(elevation, RoundedCornerShape(8.dp))
//                    .clip(RoundedCornerShape(8.dp))
//                    .align(Alignment.CenterStart)
//                    .fillMaxWidth()
//
//            ) {
//                Row(horizontalArrangement = Arrangement.SpaceBetween) {
//                    Text(text = peoble.name)
//                    Image(
//                        modifier = Modifier
//                            .size(itemHeightDp),
//                        painter = painterResource(id = R.drawable.img_avatar),
//                        contentDescription = ""
//                    )
//                }
//            }
//        }
//    }
//}