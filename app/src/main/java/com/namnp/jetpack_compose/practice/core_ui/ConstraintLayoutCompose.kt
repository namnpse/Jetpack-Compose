package com.namnp.jetpack_compose.practice.core_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Composable
fun ConstraintLayoutCompose() {
    val constraints = ConstraintSet {
        val blueBox = createRefFor("blue_box")
        val orangeBox = createRefFor("orange_box")
        val greenGuideline = createGuidelineFromStart(0.2f)

/*
        createGuidelineFromEnd(100.dp)
        createGuidelineFromEnd(0.5f)
        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Spread)
        createTopBarrier(greenBox, redBox, margin = 8.dp)
        */
        constrain(blueBox) {
            top.linkTo(parent.top)
//            start.linkTo(parent.start)
            start.linkTo(greenGuideline)
            /*width = Dimension.fillToConstraints
            width = Dimension.matchParent
            width = Dimension.wrapContent
            width = Dimension.percent(0.5f)
            width = Dimension.fillToConstraints
            width = Dimension.ratio("1.2") */// ('W:H' format) e.g:  Dimension.ratio('1:2') sets the width to be half as large as the height.
            width = Dimension.value(120.dp)
        }

        constrain(orangeBox) {
            top.linkTo(parent.top)
            start.linkTo(blueBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(80.dp)
        }
    }
    ConstraintLayout(
        constraints,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("blue_box")
        ) {

        }
        Box(modifier = Modifier
            .background(Color.Red)
            .layoutId("orange_box")
        ) {

        }
    }
}