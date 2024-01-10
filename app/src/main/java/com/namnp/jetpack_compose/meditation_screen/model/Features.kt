package com.namnp.jetpack_compose.meditation_screen.model

import com.namnp.jetpack_compose.R
import com.namnp.jetpack_compose.ui.theme.Beige1
import com.namnp.jetpack_compose.ui.theme.Beige2
import com.namnp.jetpack_compose.ui.theme.Beige3
import com.namnp.jetpack_compose.ui.theme.BlueViolet1
import com.namnp.jetpack_compose.ui.theme.BlueViolet2
import com.namnp.jetpack_compose.ui.theme.BlueViolet3
import com.namnp.jetpack_compose.ui.theme.LightGreen1
import com.namnp.jetpack_compose.ui.theme.LightGreen2
import com.namnp.jetpack_compose.ui.theme.LightGreen3
import com.namnp.jetpack_compose.ui.theme.OrangeYellow1
import com.namnp.jetpack_compose.ui.theme.OrangeYellow2
import com.namnp.jetpack_compose.ui.theme.OrangeYellow3

val features = listOf(
    MeditationFeature(
        title = "Sleep meditation",
        R.drawable.ic_headphone,
        BlueViolet1,
        BlueViolet2,
        BlueViolet3
    ),
    MeditationFeature(
        title = "Tips for sleeping",
        R.drawable.ic_videocam,
        LightGreen1,
        LightGreen2,
        LightGreen3
    ),
    MeditationFeature(
        title = "Night island",
        R.drawable.ic_headphone,
        OrangeYellow1,
        OrangeYellow2,
        OrangeYellow3
    ),
    MeditationFeature(
        title = "Calming sounds",
        R.drawable.ic_headphone,
        Beige1,
        Beige2,
        Beige3
    )
)