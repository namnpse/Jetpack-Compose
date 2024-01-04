package com.namnp.jetpack_compose.meal_app.meal_details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.namnp.jetpack_compose.meal_app.model.response.MealResponse

@Composable
fun MealDetailsScreen(meal: MealResponse?) {
    var isExpanded by remember { mutableStateOf(false) }
/*    animate for single compose
    val animatedDp: Dp by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 100.dp,
        label = "",
    )*/
    var profilePictureState by remember { mutableStateOf(MealPictureState.Normal) }
    val transition = updateTransition(targetState = profilePictureState, label = "")
    val imageSizeDp by transition.animateDp(targetValueByState = { it.size }, label = "")
    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
    val widthSize by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")

    Column {
        Row {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                border = BorderStroke(
                    width = widthSize,
                    color = color,
                )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = meal?.imageUrl)
                            .apply(block = fun ImageRequest.Builder.() {
                                transformations(CircleCropTransformation())
                            }).build()
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(imageSizeDp)
                )
            }
            Text(meal?.name ?: "default name")
        }
        Button(onClick = {
            /* for single compose
            isExpanded = !isExpanded*/

            // for multiple compose
            profilePictureState = if(profilePictureState == MealPictureState.Normal)
                MealPictureState.Expanded
            else
                MealPictureState.Normal
        }) {
            Text("Change state of meal profile picture")
        }
    }
}

enum class MealPictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}